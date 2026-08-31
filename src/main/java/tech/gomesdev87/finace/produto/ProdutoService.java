package tech.gomesdev87.finace.produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import tech.gomesdev87.finace.produto.dto.ProdutoRequestDTO;

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
}
