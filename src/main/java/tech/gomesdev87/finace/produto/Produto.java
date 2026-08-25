package tech.gomesdev87.finace.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(unique = true, length = 50)
    private String sku;

    @Column(nullable = false)
    private Integer quantidade = 0;

    @Column(name = "quantidade_minima", nullable = false)
    private Integer quantidadeMinima = 5;

    @Column(name = "valor_custo", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorCusto = BigDecimal.ZERO;

    @Column(name = "valor_venda", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorVenda = BigDecimal.ZERO;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.quantidade == null)
            this.quantidade = 0;
        if (this.quantidadeMinima == null)
            this.quantidadeMinima = 5;
        if (this.valorCusto == null)
            this.valorCusto = BigDecimal.ZERO;
        if (this.valorVenda == null)
            this.valorVenda = BigDecimal.ZERO;
        if (this.ativo == null)
            this.ativo = true;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
