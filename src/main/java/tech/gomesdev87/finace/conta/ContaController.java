package tech.gomesdev87.finace.conta;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gomesdev87.finace.conta.dto.NovaRequest;

@RestController
@RequestMapping("conta")
public class ContaController {
    private final ContaService contaService;
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/lancar")
    public ResponseEntity<Conta> newConta(Authentication authentication,@RequestBody NovaRequest request) {
        return ResponseEntity.status(200).body(this.contaService.newLançamento(authentication, request));
    }
}
