package tech.gomesdev87.finace.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.gomesdev87.finace.auth.dto.LoginRequest;
import tech.gomesdev87.finace.auth.dto.LoginResponse;
import tech.gomesdev87.finace.user.UserService;
import tech.gomesdev87.finace.user.dto.CreateUserRequest;
import tech.gomesdev87.finace.user.dto.UserResponse;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }


    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest.email(), loginRequest.senha()));
    }

    @PostMapping("register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody CreateUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }
}
