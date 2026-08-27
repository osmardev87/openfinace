package tech.gomesdev87.finace.financeiro;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;
import tech.gomesdev87.finace.auth.Token;
import tech.gomesdev87.finace.financeiro.dto.TransacaoFinanceiraRequestDTO;


@RestController
@RequestMapping("/transacoes")
public class TransacaoFinanceiraController {

    private final TransacaoFinanceiraService transacaoService;
    private final Token tokenService;



    public TransacaoFinanceiraController(TransacaoFinanceiraService transacaoService,
            Token tokenService) {
        this.transacaoService = transacaoService;
        this.tokenService = tokenService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TransacaoFinanceira>> listar(Authentication auth) {
        return ResponseEntity
                .ok(transacaoService.listFinanceira(this.tokenService.getUserId(auth)));
    }



    // 💰 CRIAR NOVA TRANSAÇÃO
    @PostMapping("/")
    public ResponseEntity<TransacaoFinanceira> criar(
            @Valid @RequestBody TransacaoFinanceiraRequestDTO dto, Authentication auth) {



        TransacaoFinanceira transacao =
                transacaoService.criar(dto, this.tokenService.getUserId(auth));

        // Retorna 201 Created com a localização do recurso
        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(transacao.getId()).toUri();

        return ResponseEntity.created(localizacao).body(transacao);
    }

}
