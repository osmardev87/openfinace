package tech.gomesdev87.finace.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tech.gomesdev87.finace.user.dto.CreateUserRequest;
import tech.gomesdev87.finace.user.dto.UserResponse;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse create(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        if (userRepository.existsByCpf(request.cpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        User user = request.toEntity();

        user.setSenha(this.passwordEncoder.encode(user.getSenha()));

        User newUser = this.userRepository.save(user);

        return UserResponse.fromEntity(newUser);

    }

}
