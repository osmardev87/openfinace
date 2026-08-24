package tech.gomesdev87.finace.auth.dto;

import tech.gomesdev87.finace.user.dto.UserResponse;

public record LoginResponse(
    String token,
    UserResponse user
) {

}
