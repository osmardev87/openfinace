package tech.gomesdev87.finace.financeiro;

import java.util.UUID;

import org.springframework.stereotype.Service;

import tech.gomesdev87.finace.financeiro.dto.TransacaoFinanceiraRequestDTO;
import tech.gomesdev87.finace.user.UserRepository;

@Service
public class TransacaoFinanceiraService {
    private final TransacaoFinanceiraRepository repositorio;
    private final UserRepository userRepository;

    public TransacaoFinanceiraService(
        UserRepository userRepository,
        TransacaoFinanceiraRepository transacaoFinanceiraRepository
    ) {
        this.repositorio = transacaoFinanceiraRepository;
        this.userRepository = userRepository;
    }

    // No seu Service, converta assim:
public TransacaoFinanceira criar(TransacaoFinanceiraRequestDTO dto, UUID userId) {

    TransacaoFinanceira transacao = TransacaoFinanceira.builder()
            .userId(userId)
            .descricao(dto.descricao())
            .categoria(dto.categoria() != null ? dto.categoria() : "OUTROS")
            .valor(dto.valor())
            .tipo(dto.tipo())
            .status(dto.status() != null ? dto.status() : "PENDENTE")
            .formaPagamento(dto.formaPagamento())
            .dataVencimento(dto.dataVencimento())
            .build();

    // Associa Parceiro se veio ID
    if (dto.parceiroId() != null) {
        transacao.setParceiro(userRepository.findById(dto.parceiroId()).orElse(null));
    }

    // // Associa Nota Fiscal se veio ID
    // if (dto.entradaNotaId() != null) {
    //     transacao.setEntradaNota(entradaNotaRepository.findById(dto.entradaNotaId()).orElse(null));
    // }

    return repositorio.save(transacao);
}
}
