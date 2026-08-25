package tech.gomesdev87.finace.user;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "users") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 255)
    private String senha;

    // UNIQUE no banco permite múltiplos nulos, contanto que sejam representados
    // como null no Java (não "")
    @Column(unique = true, length = 11)
    private String cpf;

    @Column(unique = true, length = 14)
    private String cnpj;

    @Column(length = 20)
    private String telefone;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    private Boolean ativo;

    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private Role role;

    @PrePersist
    public void prePersist() {
        dataCadastro = LocalDateTime.now();

        if (role == null) {
            role = Role.CLIENTE;
        }

        if (ativo == null) {
            ativo = true;
        }

        // Sanitização preventiva: converte Strings vazias em null para não
        // quebrar a restrição UNIQUE
        if (cpf != null && cpf.trim().isEmpty()) {
            cpf = null;
        }
        if (cnpj != null && cnpj.trim().isEmpty()) {
            cnpj = null;
        }
        if (senha != null && senha.trim().isEmpty()) {
            senha = null;
        }
    }
}