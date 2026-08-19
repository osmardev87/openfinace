package tech.gomesdev87.finace.conta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

    // ✅ Buscar todas contas de um usuário
    List<Conta> findByUserId(UUID userId);

    // ✅ Buscar por usuário e tipo (ENTRADA ou SAÍDA)
    List<Conta> findByUserIdAndTipo(UUID userId, Conta.TipoMovimento tipo);

    // ✅ Buscar por usuário e forma de pagamento
    List<Conta> findByUserIdAndFormaPagamento(UUID userId, Conta.FormaPagamento formaPagamento);
}