package plataformadecurso.demo.UserService;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import plataformadecurso.demo.User.DTO.UserRequestDTO;
import plataformadecurso.demo.User.DTO.UserResponseDTO;
import plataformadecurso.demo.User.UserEntity.UserEntity;
import plataformadecurso.demo.User.UserRepository.UserRepository;
import plataformadecurso.demo.User.UserService.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServicetest{
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void  shouldCreateUserSuccessfullyr(){

        UserRequestDTO dto = new UserRequestDTO(
           "Daniel",
           "damiel@email.com",
                "123456",
                "999999999",
                "Rua A",
                "Manuas",
                "Brasil",
                "6900000"

        );

        UserEntity savedUser = new UserEntity();

        savedUser.setId(UUID.randomUUID());
        savedUser.setName(dto.name());
        savedUser.setEmail(dto.email());

        when(passwordEncoder.encode(dto.password())).thenReturn(dto.password()).
                thenReturn("senhaCriptografada");

        when(userRepository.save(savedUser)).thenReturn(savedUser);

        UserResponseDTO response  = userService.createUser(dto);

        assertNotNull(response);
        assertEquals("Daniel", response.name());
        assertEquals("daniel@email.com", response.email());

        verify(userRepository).existsByEmail(dto.email());
        verify(passwordEncoder).encode(dto.password());
        verify(userRepository).save(any(UserEntity.class));


    }


    @Test
    void ShouldThrowExceptionWhenUserNotFound(){

        UserRequestDTO dto = new UserRequestDTO(
                "Daniel",
                "daniel@email.com",
                "123456",
                "999999999",
                "Rua A",
                "Manaus",
                "Brasil",
                "69000000"
        );
        when( userRepository.existsByEmail(dto.email())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.createUser(dto));

        assertEquals("User not found", exception.getMessage());

        verify(userRepository, never()).save(any(UserEntity.class));
    }


    @Test
    void ShoulFindUserSucessfully(){

        UUID id = UUID.randomUUID();

        UserEntity  user = createUser();

        user.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserResponseDTO response = userService.findById(id);

        assertNotNull(response);
        assertEquals(id, response.id());
        assertEquals("Daniel", response.name());

        verify(userRepository).findById(id);


    }

    private UserEntity createUser() {

        UserEntity user = new UserEntity();

        user.setId(UUID.randomUUID());
        user.setName("Daniel");
        user.setEmail("daniel@email.com");
        user.setPassword("senha");
        user.setPhone("999999999");
        user.setAddress("Rua A");
        user.setCity("Manaus");
        user.setCountry("Brasil");
        user.setZip("69000000");

        return user;
    }

    @Test
    void ShoulThowExpectionUpdatingToExistEmail(){

        UUID id = UUID.randomUUID();
        UserEntity user = createUser();
        user.setId(id);
        user.setEmail("old@email.com");


        UserRequestDTO dto = new UserRequestDTO(
                "Daniel",
                "novo@email.com",
                "123456",
                "999999999",
                "Rua A",
                "Manaus",
                "Brasil",
                "69000000"
        );

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        when(userRepository.existsByEmail(dto.email())).thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.updateUser(id, dto)
        );

        assertEquals("User with email already exists", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenDeletingUserNotFound(){
        UUID id = UUID.randomUUID();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.deleteUser(id));

        assertEquals("User not found", exception.getMessage());

        verify(userRepository, never()).deleteById(id);
    }


}
