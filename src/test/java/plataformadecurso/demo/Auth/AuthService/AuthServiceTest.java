package plataformadecurso.demo.Auth.AuthService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import plataformadecurso.demo.Auth.DTO.AuthRequestDTO;
import plataformadecurso.demo.Auth.DTO.AuthResponseDTO;
import plataformadecurso.demo.Security.JwtService;
import plataformadecurso.demo.User.UserEntity.UserEntity;
import plataformadecurso.demo.User.UserRepository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldLoginSuccessfully() {

        AuthRequestDTO request =
                new AuthRequestDTO("teste@email.com", "123456");

        UserEntity user = new UserEntity();
        user.setEmail("teste@email.com");
        user.setPassword("senhaCriptografada");

        when(userRepository.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(
                "123456",
                "senhaCriptografada"))
                .thenReturn(true);

        when(jwtService.generateToken("teste@email.com"))
                .thenReturn("jwt-token");

        AuthResponseDTO response = authService.login(request);

        assertNotNull(response);
        assertEquals("jwt-token", response.token());

        verify(userRepository).findByEmail("teste@email.com");
        verify(passwordEncoder)
                .matches("123456", "senhaCriptografada");
        verify(jwtService)
                .generateToken("teste@email.com");
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        AuthRequestDTO request =
                new AuthRequestDTO("teste@email.com", "123456");

        when(userRepository.findByEmail("teste@email.com"))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        () -> authService.login(request));

        assertEquals(
                "Usuário não encontrado",
                exception.getMessage()
        );

        verify(userRepository).findByEmail("teste@email.com");
        verifyNoInteractions(passwordEncoder);
        verifyNoInteractions(jwtService);
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsInvalid() {

        AuthRequestDTO request =
                new AuthRequestDTO("teste@email.com", "123456");

        UserEntity user = new UserEntity();
        user.setEmail("teste@email.com");
        user.setPassword("senhaCriptografada");

        when(userRepository.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(
                "123456",
                "senhaCriptografada"))
                .thenReturn(false);

        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        () -> authService.login(request));

        assertEquals(
                "Senha inválida",
                exception.getMessage()
        );

        verify(userRepository).findByEmail("teste@email.com");
        verify(passwordEncoder)
                .matches("123456", "senhaCriptografada");
        verifyNoInteractions(jwtService);
    }
}