package tech.gomesdev87.finace.user;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(length = 20)
    private String telefone;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    private Boolean ativo;

    @PrePersist
    public void prePersist() {
        dataCadastro = LocalDateTime.now();

        if (ativo == null) ativo = true;

    }

    
}