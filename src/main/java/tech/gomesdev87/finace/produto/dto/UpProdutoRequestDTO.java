package tech.gomesdev87.finace.produto.dto;

import java.math.BigDecimal;

public record UpProdutoRequestDTO(String nome, String descricao, String sku, Integer quantidade,
        Integer quantidadeMinima, BigDecimal valorCusto, BigDecimal valorVenda) {
}
