package plataformadecurso.demo.User.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import plataformadecurso.demo.User.DTO.UserRequestDTO;
import plataformadecurso.demo.User.DTO.UserResponseDTO;
import plataformadecurso.demo.User.UserEntity.UserEntity;
import plataformadecurso.demo.User.UserRepository.UserRepository;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserRequestDTO dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email já cadastrado");
        }

        UserEntity user = new UserEntity();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));

        // user.setRole(dto.role());
        user.setPhone(dto.phone());
        user.setAddress(dto.address());
        user.setCity(dto.city());
        //user.setState(dto.state());
        user.setCountry(dto.country());
        user.setZip(dto.zip());

        UserEntity savedUser = userRepository.save(user);

        return UserResponseDTO.fromEntity(savedUser);
    }

    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::fromEntity)
                .toList();
    }

    public UserResponseDTO findById(UUID id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        return UserResponseDTO.fromEntity(user);
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO dto) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        if (!user.getEmail().equals(dto.email())
                && userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email já cadastrado");
        }

        user.setName(dto.name());
        user.setEmail(dto.email());

        // user.setRole(dto.role());
        user.setPhone(dto.phone());
        user.setAddress(dto.address());
        user.setCity(dto.city());
        //user.setState(dto.state());
        user.setCountry(dto.country());
        user.setZip(dto.zip());

        if (dto.password() != null && !dto.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.password()));
        }

        UserEntity updatedUser = userRepository.save(user);

        return UserResponseDTO.fromEntity(updatedUser);
    }

    public void deleteUser(UUID id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        userRepository.delete(user);
    }
}