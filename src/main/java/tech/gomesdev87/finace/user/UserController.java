package tech.gomesdev87.finace.user;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;
import tech.gomesdev87.finace.auth.Token;
import tech.gomesdev87.finace.user.dto.UpdateRequest;
import tech.gomesdev87.finace.user.dto.UserResponse;

@RestController
@RequestMapping("/transacoes")
public class UserController {
    private final UserService userService;
    private final Token tokenService;

    public UserController(UserService userService, Token tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PatchMapping ("/")
    public ResponseEntity<UserResponse> atualizarUsuario(
        @Valid  @RequestBody UpdateRequest user ,
         Authentication auth
        ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.atualizarUsuario(this.tokenService.getUserId(auth) , user));
    }
}
