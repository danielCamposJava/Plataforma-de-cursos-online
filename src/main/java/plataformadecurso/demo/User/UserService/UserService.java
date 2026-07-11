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

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        if( userRepository.existsById(UUID.fromString(userRequestDTO.email()))){
             throw  new RuntimeException("Email ja existente");
         }

         UserEntity userEntity = new UserEntity();

         userEntity.setName(userRequestDTO.name());
         userEntity.setEmail(userRequestDTO.email());
         userEntity.setPassword(passwordEncoder.encode(userRequestDTO.password()));

         UserEntity saved = userRepository.save(userEntity);
         return  UserResponseDTO.fromEntity(saved);

    }

   public List<UserResponseDTO> findAllUser(){
        return  userRepository.findAll().stream().map(
                UserResponseDTO ::fromEntity
        ).toList();
   }

   public UserResponseDTO updateUser(UUID id , UserRequestDTO userRequestDTO) {

       userRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("User não econtrado"));

       UserEntity userEntity = new UserEntity();
       userEntity.setName(userRequestDTO.name());
       userEntity.setEmail(userRequestDTO.email());
       userEntity.setPassword(passwordEncoder.encode(userRequestDTO.password()));

       UserEntity saved = userRepository.save(userEntity);
       return UserResponseDTO.fromEntity(saved);
   }

   public  void deleteUser(UUID id ){
        if(!userRepository.existsById(id)){
            throw  new RuntimeException("User not found");
        }
   }

}
