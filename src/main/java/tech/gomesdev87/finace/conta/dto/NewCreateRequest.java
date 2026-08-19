package tech.gomesdev87.finace.conta.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import tech.gomesdev87.finace.conta.enums.FormaPagamento;
import tech.gomesdev87.finace.conta.enums.TipoMovimento;

public record NewCreateRequest(
        UUID userId,
        String desc,
        BigDecimal valor,
        LocalDate data,
        LocalDate dataVencimento,
        TipoMovimento tipoMovimento,
       FormaPagamento formaPagamento
) {
}

