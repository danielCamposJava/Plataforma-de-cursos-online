package plataformadecurso.demo.User.UserService;

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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
        UserRequestDTO dto = createDummyRequestDTO("daniel@email.com");
        UserEntity savedUser = createDummyUserEntity();

        when(userRepository.existsByEmail(dto.email())).thenReturn(false);
        when(passwordEncoder.encode(dto.password())).thenReturn("senhaCriptografada");
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        UserResponseDTO response = userService.createUser(dto);

        assertNotNull(response);
        assertEquals("Daniel", response.name());
        assertEquals("daniel@email.com", response.email());

        verify(userRepository).existsByEmail(dto.email());
        verify(passwordEncoder).encode(dto.password());
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExistsOnCreate() {
        UserRequestDTO dto = createDummyRequestDTO("daniel@email.com");

        when(userRepository.existsByEmail(dto.email())).thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.createUser(dto)
        );

        assertEquals("Email já cadastrado", exception.getMessage());
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void shouldFindAllUsersSuccessfully() {
        UserEntity user1 = createDummyUserEntity();
        UserEntity user2 = createDummyUserEntity();
        user2.setEmail("outro@email.com");

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<UserResponseDTO> response = userService.findAllUsers();

        assertNotNull(response);
        assertEquals(2, response.size());

        verify(userRepository).findAll();
    }

    @Test
    void shouldFindUserByIdSuccessfully() {
        UUID id = UUID.randomUUID();

        UserEntity user = createDummyUserEntity();
        user.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserResponseDTO response = userService.findById(id);

        assertNotNull(response);
        assertEquals(id, response.id());

        verify(userRepository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundById() {
        UUID id = UUID.randomUUID();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.findById(id)
        );

        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    void shouldUpdateUserWithoutChangingEmailSuccessfully() {
        UUID id = UUID.randomUUID();

        UserEntity existingUser = createDummyUserEntity();
        existingUser.setId(id);
        existingUser.setEmail("daniel@email.com");

        UserRequestDTO updateDto = createDummyRequestDTO("daniel@email.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode(updateDto.password())).thenReturn("novaSenhaCripto");
        when(userRepository.save(any(UserEntity.class))).thenReturn(existingUser);

        UserResponseDTO response = userService.updateUser(id, updateDto);

        assertNotNull(response);

        verify(userRepository, never()).existsByEmail(anyString());
        verify(userRepository).save(existingUser);
    }

    @Test
    void shouldUpdateUserWithNewEmailSuccessfully() {
        UUID id = UUID.randomUUID();

        UserEntity existingUser = createDummyUserEntity();
        existingUser.setId(id);
        existingUser.setEmail("antigo@email.com");

        UserRequestDTO updateDto = createDummyRequestDTO("novo@email.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(userRepository.existsByEmail("novo@email.com")).thenReturn(false);
        when(passwordEncoder.encode(updateDto.password())).thenReturn("novaSenhaCripto");
        when(userRepository.save(any(UserEntity.class))).thenReturn(existingUser);

        UserResponseDTO response = userService.updateUser(id, updateDto);

        assertNotNull(response);

        verify(userRepository).existsByEmail("novo@email.com");
        verify(userRepository).save(existingUser);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingToExistingEmail() {
        UUID id = UUID.randomUUID();

        UserEntity existingUser = createDummyUserEntity();
        existingUser.setId(id);
        existingUser.setEmail("antigo@email.com");

        UserRequestDTO updateDto = createDummyRequestDTO("existente@email.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(userRepository.existsByEmail("existente@email.com")).thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.updateUser(id, updateDto)
        );

        assertEquals("Email já cadastrado", exception.getMessage());

        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void shouldDeleteUserSuccessfully() {
        UUID id = UUID.randomUUID();

        UserEntity user = createDummyUserEntity();
        user.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        doNothing().when(userRepository).delete(user);

        assertDoesNotThrow(() -> userService.deleteUser(id));

        verify(userRepository).findById(id);
        verify(userRepository).delete(user);
    }

    @Test
    void shouldThrowExceptionWhenDeletingUserNotFound() {
        UUID id = UUID.randomUUID();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.deleteUser(id)
        );

        assertEquals("Usuário não encontrado", exception.getMessage());

        verify(userRepository, never()).delete(any(UserEntity.class));
    }

    private UserRequestDTO createDummyRequestDTO(String email) {
        return new UserRequestDTO(
                "Daniel",
                email,
                "123456",
                "999999999",
                "Rua A",
                "Manaus",
                "Brasil",
                "69000000"
        );
    }

    private UserEntity createDummyUserEntity() {
        UserEntity user = new UserEntity();

        user.setId(UUID.randomUUID());
        user.setName("Daniel");
        user.setEmail("daniel@email.com");
        user.setPassword("senhaCriptografada");
        user.setPhone("999999999");
        user.setAddress("Rua A");
        user.setCity("Manaus");
        user.setCountry("Brasil");
        user.setZip("69000000");

        return user;
    }
}