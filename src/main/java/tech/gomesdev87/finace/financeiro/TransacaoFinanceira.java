package tech.gomesdev87.finace.financeiro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.gomesdev87.finace.entrada.EntradaNota;
import tech.gomesdev87.finace.user.User;

@Entity @Table(name = "transacoes_financeiras") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TransacaoFinanceira {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Dono do registro (usuário que fez o lançamento)
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    // Cliente ou Fornecedor associado à transação (reutilizando a tabela users)
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "parceiro_id")
    private User parceiro;

    // Nota fiscal de compra associada, se houver
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "entrada_nota_id")
    private EntradaNota entradaNota;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false, length = 100)
    private String categoria;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false, length = 10)
    private String tipo; // RECEITA ou DESPESA

    @Column(nullable = false, length = 15)
    private String status; // PENDENTE, PAGO, CANCELADO

    @Column(name = "forma_pagamento", length = 20)
    private String formaPagamento;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null)
            this.status = "PENDENTE";
        if (this.categoria == null)
            this.categoria = "OUTROS";
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}