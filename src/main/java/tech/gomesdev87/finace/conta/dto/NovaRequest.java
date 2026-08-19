package tech.gomesdev87.finace.conta.dto;

import tech.gomesdev87.finace.conta.Conta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record NovaRequest(
        String desc,
        BigDecimal valor,
        LocalDate data,
        LocalDate dataVencimento,
        Conta.TipoMovimento tipoMovimento,
        Conta.FormaPagamento formaPagamento
) {
}
