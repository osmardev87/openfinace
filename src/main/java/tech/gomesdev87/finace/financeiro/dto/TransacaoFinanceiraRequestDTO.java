package tech.gomesdev87.finace.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TransacaoFinanceiraRequestDTO(

        @NotBlank(message = "Descrição é obrigatória")
        @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
        String descricao,

        @Size(max = 100, message = "Categoria deve ter no máximo 100 caracteres")
        String categoria,

        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser maior que zero")
        BigDecimal valor,

        @NotBlank(message = "Tipo é obrigatório — RECEITA ou DESPESA")
        String tipo,

        String status, // Opcional — padrão é PENDENTE

        @Size(max = 20, message = "Forma de pagamento excedeu o limite")
        String formaPagamento,

        @NotNull(message = "Data de vencimento é obrigatória")
        LocalDate dataVencimento,

        UUID parceiroId,       // Opcional — ID do Cliente/Fornecedor
        UUID entradaNotaId     // Opcional — ID da Nota Fiscal
) {}