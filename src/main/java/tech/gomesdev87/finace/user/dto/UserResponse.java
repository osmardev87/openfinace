package tech.gomesdev87.finace.user.dto;

import java.util.UUID;

import tech.gomesdev87.finace.user.Role;
import tech.gomesdev87.finace.user.User;

public record UserResponse(
        UUID id,
        String nome,
        String email,
        String cpf,
        String cnpj, // Adicionado CNPJ no retorno
        String telefone,
        Role role) {
    public static UserResponse fromEntity(User user) {
        return new UserResponse(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getCpf(),
                user.getCnpj(), // Novo campo mapeado da entidade
                user.getTelefone(),
                user.getRole());
    }
}