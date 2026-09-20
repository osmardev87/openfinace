package tech.gomesdev87.finace.user.dto;

import javax.management.relation.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateRequest(
        @NotBlank(message = "Nome é obrigatório") @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres") String nome,
        
        String empresa,
        
        String logo,

        @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 números") String cnpj,

        @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres") String telefone
       
    ) {

}
