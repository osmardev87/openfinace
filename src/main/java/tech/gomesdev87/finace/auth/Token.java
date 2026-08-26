package tech.gomesdev87.finace.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import tech.gomesdev87.finace.auth.dto.JwtUserData;
import tech.gomesdev87.finace.user.User;

import java.time.Instant;
import java.util.UUID;

@Component
public class Token {
    private final JwtEncoder jwtEncoder;

    private Token(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String gerarToken(User user, Long expiresIn) {

        var now = Instant.now();
        JwtClaimsSet clains = JwtClaimsSet.builder()
                .issuer("finace")
                .claim("email", user.getEmail())
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256)
                .keyId("conectafinace-secret-key")
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(header, clains)).getTokenValue();
    }

    public JwtUserData getToken(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String userId = jwt.getSubject();
        String email = jwt.getClaimAsString("email");

        return new JwtUserData(userId, email);
    }

    // ✅ Método ÚNICO — extrai e converte o ID do usuário logado
    public UUID getUserId(Authentication auth) {
        JwtUserData dadosUsuario = this.getToken(auth);
        return UUID.fromString(dadosUsuario.userId());
    }

}
