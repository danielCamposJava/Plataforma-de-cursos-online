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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUserSuccessfully() {

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

        UserEntity savedUser = createUser();

        when(userRepository.existsByEmail(dto.email()))
                .thenReturn(false);

        when(passwordEncoder.encode(dto.password()))
                .thenReturn("senhaCriptografada");

        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(savedUser);

        UserResponseDTO response = userService.createUser(dto);

        assertNotNull(response);
        assertEquals("Daniel", response.name());
        assertEquals("daniel@email.com", response.email());

        verify(userRepository).existsByEmail(dto.email());
        verify(passwordEncoder).encode(dto.password());
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

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

        when(userRepository.existsByEmail(dto.email()))
                .thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.createUser(dto)
        );

        assertEquals(
                "Email já cadastrado",
                exception.getMessage()
        );

        verify(userRepository, never())
                .save(any(UserEntity.class));
    }

    @Test
    void shouldFindUserSuccessfully() {

        UUID id = UUID.randomUUID();

        UserEntity user = createUser();
        user.setId(id);

        when(userRepository.findById(id))
                .thenReturn(Optional.of(user));

        UserResponseDTO response = userService.findById(id);

        assertNotNull(response);
        assertEquals(id, response.id());
        assertEquals("Daniel", response.name());

        verify(userRepository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        UUID id = UUID.randomUUID();

        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.findById(id)
        );

        assertEquals(
                "Usuário não encontrado",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenUpdatingToExistingEmail() {

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

        when(userRepository.findById(id))
                .thenReturn(Optional.of(user));

        when(userRepository.existsByEmail(dto.email()))
                .thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.updateUser(id, dto)
        );

        assertEquals(
                "Email já cadastrado",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenDeletingUserNotFound() {

        UUID id = UUID.randomUUID();

        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.deleteUser(id)
        );

        assertEquals(
                "Usuário não encontrado",
                exception.getMessage()
        );

        verify(userRepository, never())
                .delete(any(UserEntity.class));
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
}