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

    public UserResponseDTO createUser(UserRequestDTO dto){

        if(userRepository.existsByEmail(dto.email())){
            throw new RuntimeException("Email já existente");
        }


        UserEntity user = new UserEntity();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(
                passwordEncoder.encode(dto.password())
        );

        return UserResponseDTO.fromEntity(
                userRepository.save(user)
        );
    }

    public List<UserResponseDTO> findAllUser(){

        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::fromEntity)
                .toList();
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO dto){

        UserEntity user = userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Usuário não encontrado")
                );

        user.setName(dto.name());
        user.setEmail(dto.email());

        if(dto.password() != null){
            user.setPassword(
                    passwordEncoder.encode(dto.password())
            );
        }

        return UserResponseDTO.fromEntity(
                userRepository.save(user)
        );

    }

    public void deleteUser(UUID id){

        if(!userRepository.existsById(id)){
            throw new RuntimeException("Usuário não encontrado");
        }

        userRepository.deleteById(id);
    }

}