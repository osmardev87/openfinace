package tech.gomesdev87.finace.conta;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import tech.gomesdev87.finace.auth.Token;
import tech.gomesdev87.finace.conta.dto.NovaRequest;

import java.util.UUID;

@Service
public class ContaService {
    private final Token token;

    private final ContaRepository contaRepository;

    public ContaService(Token token, ContaRepository contaRepository) {
        this.token = token;
        this.contaRepository = contaRepository;
    }

    public Conta newLançamento(Authentication authentication, NovaRequest request) {
        String userId = token.getToken(authentication).userId();

        Conta  conta = new Conta();
        conta.setUserId(UUID.fromString(userId));
        conta.setData(request.data());
        conta.setValor(request.valor());
        conta.setDesc(request.desc());
        conta.setDataVencimento(request.dataVencimento());
        conta.setFormaPagamento(request.formaPagamento());
        conta.setTipo(request.tipoMovimento());

        return contaRepository.save(conta);
    }

}
