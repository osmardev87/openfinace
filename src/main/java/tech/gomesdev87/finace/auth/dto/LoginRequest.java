package tech.gomesdev87.finace.auth.dto;

public record LoginRequest(
        String email,
        String senha
) {
}
