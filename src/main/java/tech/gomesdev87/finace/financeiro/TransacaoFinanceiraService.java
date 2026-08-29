package tech.gomesdev87.finace.financeiro;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import tech.gomesdev87.finace.financeiro.dto.TransacaoFinanceiraRequestDTO;

@Service
public class TransacaoFinanceiraService {
    private final TransacaoFinanceiraRepository repositorio;

    public TransacaoFinanceiraService(
            TransacaoFinanceiraRepository transacaoFinanceiraRepository) {
        this.repositorio = transacaoFinanceiraRepository;
    }

    // No seu Service, converta assim:
    public TransacaoFinanceira criar(TransacaoFinanceiraRequestDTO dto, UUID userId) {

        TransacaoFinanceira transacao = TransacaoFinanceira.builder().userId(userId)
                .descricao(dto.descricao())
                .categoria(dto.categoria() != null ? dto.categoria() : "OUTROS").valor(dto.valor())
                .tipo(dto.tipo()).status(dto.status() != null ? dto.status() : "PENDENTE")
                .formaPagamento(dto.formaPagamento()).dataVencimento(dto.dataVencimento()).build();

        // Associa Parceiro se veio ID
        // if (dto.parceiroId() != null) {
        // transacao.setParceiro(userRepository.findById(dto.parceiroId()).orElse(null));
        // }

        // // Associa Nota Fiscal se veio ID
        // if (dto.entradaNotaId() != null) {
        // transacao.setEntradaNota(entradaNotaRepository.findById(dto.entradaNotaId()).orElse(null));
        // }

        return repositorio.save(transacao);
    }

    public TransacaoFinanceira atualizar(UUID id, TransacaoFinanceiraRequestDTO dto, UUID userId) {
        TransacaoFinanceira transacaoExistente = repositorio.findByUserIdAndId(userId, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Transação não encontrada para o usuário."));

        transacaoExistente.setDescricao(dto.descricao());
        transacaoExistente.setCategoria(dto.categoria() != null ? dto.categoria() : "OUTROS");
        transacaoExistente.setValor(dto.valor());
        transacaoExistente.setTipo(dto.tipo());
        transacaoExistente.setStatus(dto.status() != null ? dto.status() : "PENDENTE");
        transacaoExistente.setFormaPagamento(dto.formaPagamento());
        transacaoExistente.setDataVencimento(dto.dataVencimento());

        return repositorio.save(transacaoExistente);
    }

    public List<TransacaoFinanceira> listFinanceira(UUID id) {
        return this.repositorio.findByUserId(id);
    }

    public void deletar(UUID id, UUID userId) {
        TransacaoFinanceira transacaoExistente = repositorio.findByUserIdAndId(userId, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Transação não encontrada para o usuário."));

        repositorio.delete(transacaoExistente);
    }
}
