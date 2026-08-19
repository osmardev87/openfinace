package tech.gomesdev87.finace.auth;


import java.time.Duration;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tech.gomesdev87.finace.user.User;
import tech.gomesdev87.finace.user.UserRepository;

@Service
public class AuthService {
    private final UserRepository repo;
    private final Token token;
    private final BCryptPasswordEncoder bCrypt;
    public AuthService(UserRepository repo, Token token, BCryptPasswordEncoder bCrypt) {
        this.repo = repo;
        this.token = token;
        this.bCrypt = bCrypt;
    }

    public String login(String email, String password){
        User user = repo.findByEmail(email).orElseThrow( () ->
                new BadCredentialsException("E-mail e senha invalidos")
        );

        if(!bCrypt.matches(password, user.getSenha())) {
            throw  new BadCredentialsException("E-mail e senha invalidos");
        }

        long expiresIn = Duration.ofDays(30).getSeconds(); // 30 dias

        return this.token.gerarToken(user, expiresIn);
    }
}
