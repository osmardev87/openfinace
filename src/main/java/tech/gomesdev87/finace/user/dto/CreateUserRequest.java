package tech.gomesdev87.finace.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import tech.gomesdev87.finace.user.Role;
import tech.gomesdev87.finace.user.User;

public record CreateUserRequest(
        @NotBlank(message = "Nome é obrigatório") @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres") String nome,

        @NotBlank(message = "E-mail é obrigatório") @Email(message = "E-mail inválido") @Size(max = 150, message = "E-mail deve ter no máximo 150 caracteres") String email,

        // Sem @NotBlank. Validação de tamanho só é aplicada se a senha for
        // preenchida
        @Size(min = 8, max = 100, message = "Senha deve ter entre 8 e 100 caracteres") String senha,

        // Sem @NotBlank. O padrão de 11 dígitos só será cobrado se o CPF for
        // preenchido
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 números") String cpf,

        // Novo campo CNPJ. O padrão de 14 dígitos só será cobrado se for
        // preenchido
        @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 números") String cnpj,

        @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres") String telefone,

        Role role) {

    public User toEntity() {
        User user = new User();
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senha);
        user.setCpf(cpf);
        user.setCnpj(cnpj); // Novo campo
        user.setTelefone(telefone);
        user.setRole(role != null ? role : Role.CLIENTE);
        return user;
    }
}