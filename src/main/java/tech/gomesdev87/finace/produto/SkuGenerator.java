package tech.gomesdev87.finace.produto;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Component;

@Component
public class SkuGenerator {
    private final ProdutoRepository produtoRepository;

    public SkuGenerator(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public String gerarSkuUnico(String nomeProduto, UUID userId) {
        String sku;
        boolean jaExiste;

        do {
            sku = criarEstruturaSku(nomeProduto);
            // Garante que o SKU gerado é verdadeiramente único para este usuário no banco
            jaExiste = produtoRepository.existsBySkuAndUserId(sku, userId);
        } while (jaExiste);

        return sku;
    }

    private String criarEstruturaSku(String nome) {
        String prefixo = "PROD";
        if (nome != null && nome.trim().length() >= 3) {
            prefixo = nome.trim().substring(0, 3).replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        }

        int ano = LocalDateTime.now().getYear();
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(1000, 9999);

        return prefixo + "-" + ano + "-" + numeroAleatorio;
    }
}
