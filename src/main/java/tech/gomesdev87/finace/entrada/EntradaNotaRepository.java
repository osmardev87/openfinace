package tech.gomesdev87.finace.entrada;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaNotaRepository extends JpaRepository<EntradaNota, UUID> {
    // Busca uma nota fiscal pela chave de acesso de 44 dígitos
    Optional<EntradaNota> findByChaveAcessoAndUserId(String chaveAcesso, UUID userId);

    // Verifica se uma nota com esta chave de acesso já foi cadastrada para o usuário
    boolean existsByChaveAcessoAndUserId(String chaveAcesso, UUID userId);

    // Lista todas as notas de um usuário específico
    List<EntradaNota> findByUserId(UUID userId);

    // Busca notas de um determinado fornecedor (por CPF/CNPJ) de um usuário
    List<EntradaNota> findByFornecedorCpfCnpjAndUserId(String fornecedorCpfCnpj, UUID userId);
}
