package tech.gomesdev87.finace.produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    // Busca um produto pelo SKU e pertencente a um usuário específico
    Optional<Produto> findBySkuAndUserId(String sku, UUID userId);

    // Lista todos os produtos de um usuário específico
    List<Produto> findByUserId(UUID userId);

    // Verifica se um SKU já está cadastrado para o usuário
    boolean existsBySkuAndUserId(String sku, UUID userId);

    // Consulta para listar produtos com quantidade abaixo do estoque mínimo
    @Query("SELECT p FROM Produto p WHERE p.userId = :userId AND p.quantidade < p.quantidadeMinima AND p.ativo = true")
    List<Produto> findProdutosComEstoqueBaixo(@Param("userId") UUID userId);

    // ✅ CORRIGIDO: Adicionado "And" para separar os atributos Nome e UserId
    Optional<Produto> findByNomeAndUserId(String nome, UUID userId);
}
