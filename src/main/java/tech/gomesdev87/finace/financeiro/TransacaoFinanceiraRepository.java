package tech.gomesdev87.finace.financeiro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransacaoFinanceiraRepository extends JpaRepository<TransacaoFinanceira, UUID> {

    // Lista todas as transações de um usuário específico
    List<TransacaoFinanceira> findByUserId(UUID userId);

    // Busca transações por intervalo de data de vencimento
    List<TransacaoFinanceira> findByUserIdAndDataVencimentoBetween(UUID userId, LocalDate inicio, LocalDate fim);

    // Busca transações pendentes ou pagas
    List<TransacaoFinanceira> findByUserIdAndStatus(UUID userId, String status);

    // Consulta para somar o total de Receitas (entradas) em um período
    @Query("SELECT SUM(t.valor) FROM TransacaoFinanceira t WHERE t.userId = :userId AND t.tipo = 'RECEITA' AND t.status = 'PAGO' AND t.dataVencimento BETWEEN :inicio AND :fim")
    BigDecimal somarReceitasRealizadasPeriodo(@Param("userId") UUID userId, @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);

    // Consulta para somar o total de Despesas (saídas) em um período
    @Query("SELECT SUM(t.valor) FROM TransacaoFinanceira t WHERE t.userId = :userId AND t.tipo = 'DESPESA' AND t.status = 'PAGO' AND t.dataVencimento BETWEEN :inicio AND :fim")
    BigDecimal somarDespesasRealizadasPeriodo(@Param("userId") UUID userId, @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);
}