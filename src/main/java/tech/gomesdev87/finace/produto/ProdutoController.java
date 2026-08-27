package tech.gomesdev87.finace.produto;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tech.gomesdev87.finace.auth.Token;
import tech.gomesdev87.finace.produto.dto.ProdutoRequestDTO;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final Token token;

    public ProdutoController(
            ProdutoService produtoService,
            Token token) {

        this.produtoService = produtoService;
        this.token = token;
    }


    @PostMapping("/")
    public ResponseEntity<Produto> create(
            @Valid @RequestBody ProdutoRequestDTO prod,
            Authentication auth) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.produtoService.cadastrarProduto(prod, this.token.getUserId(auth)));
    }

    @GetMapping("/")
    public ResponseEntity<List<Produto>> list(Authentication auth) {
        return ResponseEntity.ok(
            this.produtoService.listProdutos(this.token.getUserId(auth)));
    }
}