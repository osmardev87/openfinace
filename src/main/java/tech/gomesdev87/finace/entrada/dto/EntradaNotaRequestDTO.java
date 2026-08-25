package tech.gomesdev87.finace.entrada.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EntradaNotaRequestDTO(
                @NotBlank(message = "A chave de acesso da nota é obrigatória") @Size(min = 44, max = 44, message = "A chave de acesso da NF-e deve ter exatamente 44 dígitos") String chaveAcesso,

                String numeroNota,
                String serie,
                LocalDate dataEmissao,
                String fornecedorNome,
                String fornecedorCpfCnpj) {
}
