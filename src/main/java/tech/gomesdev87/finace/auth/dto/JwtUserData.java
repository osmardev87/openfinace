package tech.gomesdev87.finace.auth.dto;

public record JwtUserData(
        String userId,
        String email
) {
}