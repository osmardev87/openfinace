package tech.gomesdev87.finace.produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.gomesdev87.finace.produto.dto.ProdutoRequestDTO;
import tech.gomesdev87.finace.produto.dto.UpProdutoRequestDTO;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final SkuGenerator skuGenerator;

    public ProdutoService(ProdutoRepository produtoRepository, SkuGenerator skuGenerator) {
        this.produtoRepository = produtoRepository;
        this.skuGenerator = skuGenerator;
    }

    public Produto cadastrarProduto(ProdutoRequestDTO dto, UUID userId) {
        // 1. Determina o SKU (usa o do DTO ou gera um novo)
        String skuFinal = dto.sku();
        if (skuFinal == null || skuFinal.trim().isEmpty()) {
            skuFinal = skuGenerator.gerarSkuUnico(dto.nome(), userId);
        }
        // 2. Cria a entidade Produto a partir do DTO e do SKU final
        Produto novoProduto = Produto.builder().nome(dto.nome()).descricao(dto.descricao())
                .sku(skuFinal).quantidade(dto.quantidade()).quantidadeMinima(dto.quantidadeMinima())
                .valorCusto(dto.valorCusto() != null ? dto.valorCusto() : BigDecimal.ZERO)
                .valorVenda(dto.valorVenda()).userId(userId).ativo(true).build();
        // 3. Salva a entidade no banco de dados
        return produtoRepository.save(novoProduto);

    }

    public List<Produto> listProdutos(UUID userId) {
        return this.produtoRepository.findByUserId(userId);
    }

    public List<Produto> uProdutos(UUID userId, UpProdutoRequestDTO dto) {
        // ✅ SKU é opcional no DTO → validação antes de buscar
        if (dto.sku() == null || dto.sku().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O SKU deve ser informado para identificar o produto a ser atualizado");
        }

        // ✅ Busca segura por SKU + pertencimento ao usuário
        Produto produto = produtoRepository.findBySkuAndUserId(dto.sku(), userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com SKU \""
                        + dto.sku() + "\" não encontrado ou não pertence ao usuário"));

        // ✅ Campos OBRIGATÓRIOS no DTO → atribuição direta (sem null)
        if (dto.nome() != null) {
            produto.setNome(dto.nome());
        }
        if (dto.quantidade() != null) {
            produto.setQuantidade(dto.quantidade());
        }

        if (dto.quantidadeMinima() != null) {
            produto.setQuantidadeMinima(dto.quantidadeMinima());
        }
        if (dto.valorVenda() != null) {
            produto.setValorVenda(dto.valorVenda());
        }

        // ✅ Campos OPCIONAIS → decide: sobrescrever OU manter atual
        // OPÇÃO 1: Sobrescrever com null se vier vazio (comportamento atual do seu código)
        if (dto.descricao() != null) {
            produto.setDescricao(dto.descricao());
        }
        if (dto.valorCusto() != null) {
            produto.setValorCusto(dto.valorCusto());
        } else {
            produto.setValorCusto(BigDecimal.ZERO);
        }

        // ✅ Validação de regra de negócio complementar
        if (produto.getValorVenda().compareTo(produto.getValorCusto()) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Valor de venda não pode ser menor que o valor de custo");
        }

        produtoRepository.save(produto);


        return this.produtoRepository.findProdutosComEstoqueBaixo(userId);
    }

    public String deleteProduto(String sku, UUID userId) { // ❗ Era void → agora retorna String
        Produto produto = produtoRepository.findBySkuAndUserId(sku, userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        produtoRepository.delete(produto);
        return "Produto com SKU " + sku + " removido com sucesso!"; // ✅ Retorna texto
    }
}
