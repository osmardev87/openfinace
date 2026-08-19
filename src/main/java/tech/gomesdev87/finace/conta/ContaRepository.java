package tech.gomesdev87.finace.conta;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.gomesdev87.finace.conta.enums.FormaPagamento;
import tech.gomesdev87.finace.conta.enums.TipoMovimento;

public interface ContaRepository extends JpaRepository<Conta, UUID> {

    // ✅ Buscar todas contas de um usuário
    List<Conta> findByUserId(UUID userId);

    // ✅ Buscar por usuário e tipo (ENTRADA ou SAÍDA)
    List<Conta> findByUserIdAndTipo(UUID userId, TipoMovimento tipo);

    // ✅ Buscar por usuário e forma de pagamento
    List<Conta> findByUserIdAndFormaPagamento(UUID userId, FormaPagamento formaPagamento);
}