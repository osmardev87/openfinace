package tech.gomesdev87.finace.entrada.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EntradaNotaResponseDTO(
        UUID id,
        String chaveAcesso,
        String numeroNota,
        String serie,
        LocalDate dataEmissao,
        LocalDateTime dataEntrada,
        String fornecedorNome,
        String fornecedorCpfCnpj,
        BigDecimal valorTotal) {
}
