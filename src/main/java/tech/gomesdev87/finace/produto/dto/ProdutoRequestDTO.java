package tech.gomesdev87.finace.produto.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoRequestDTO(
        @NotBlank(message = "O nome do produto é obrigatório") @Size(max = 150,
                message = "O nome do produto não pode exceder 150 caracteres") String nome,

        String descricao,

        @Size(max = 50, message = "O SKU não pode exceder 50 caracteres") String sku, // Pode ser
                                                                                      // nulo para
                                                                                      // geração
                                                                                      // automática

        @NotNull(message = "A quantidade é obrigatória") @Min(value = 0,
                message = "A quantidade não pode ser menor que zero") Integer quantidade,

        @NotNull(message = "A quantidade mínima em estoque é obrigatória") @Min(value = 0,
                message = "A quantidade mínima não pode ser menor que zero") Integer quantidadeMinima,

        @NotNull(message = "O valor de custo é obrigatório") @Min(value = 0,
                message = "O valor de custo deve ser maior ou igual a zero") BigDecimal valorCusto,

        @NotNull(message = "O valor de venda é obrigatório") @Min(value = 0,
                message = "O valor de venda deve ser maior ou igual a zero") BigDecimal valorVenda) {
}
