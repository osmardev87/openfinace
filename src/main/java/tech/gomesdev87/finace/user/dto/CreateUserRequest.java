package tech.gomesdev87.finace.user.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import tech.gomesdev87.finace.user.User;

public record CreateUserRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        @Size(max = 150, message = "E-mail deve ter no máximo 150 caracteres")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 8, max = 100, message = "Senha deve ter entre 8 e 100 caracteres")
        String senha,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(
                regexp = "\\d{11}",
                message = "CPF deve conter 11 números"
        )
        String cpf,

        @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
        String telefone
) {

    public User toEntity() {
        User user = new User();
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senha);
        user.setCpf(cpf);
        user.setTelefone(telefone);
        return user;
        
    }
}