package tech.gomesdev87.finace.user;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tech.gomesdev87.finace.user.dto.CreateUserRequest;
import tech.gomesdev87.finace.user.dto.UpdateRequest;
import tech.gomesdev87.finace.user.dto.UserResponse;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse atualizarUsuario(UUID id, UpdateRequest user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Atualiza os campos do usuário existente com os valores do objeto recebido
        existingUser.setNome(user.nome());
        existingUser.setEmpresa(user.empresa());
        existingUser.setLogo(user.logo());
        existingUser.setTelefone(user.telefone());
        existingUser.setCnpj(user.cnpj());

        // Salva as alterações no banco de dados
        User newUser = userRepository.save(existingUser);
        return UserResponse.fromEntity(newUser);
    }

    public UserResponse create(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        if (userRepository.existsByCpf(request.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        if (userRepository.existsByCnpj(request.cnpj())) {
            throw new IllegalArgumentException("CNPJ já cadastrado");
        }

        if ((request.role() == Role.ADMIN || request.role() == Role.VENDEDOR)
                && (request.senha() == null || request.senha().isBlank())) {
            throw new IllegalArgumentException("Senha é obrigatória para Administradores e Vendedores");
        }

        User user = request.toEntity();

        user.setSenha(this.passwordEncoder.encode(user.getSenha()));

        User newUser = this.userRepository.save(user);

        return UserResponse.fromEntity(newUser);

    }

}
