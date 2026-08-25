package tech.gomesdev87.finace.produto.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoResponseDTO(UUID id, String nome, String descricao, String sku,
        Integer quantidade, Integer quantidadeMinima, BigDecimal valorCusto, BigDecimal valorVenda,
        Boolean ativo, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
