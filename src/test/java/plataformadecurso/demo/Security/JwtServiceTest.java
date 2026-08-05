package plataformadecurso.demo.Security;

import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void shouldgenerateToken() {

    String token = jwtService.generateToken(
            "test@email.com"
    );

    assertNotNull(token);
    assertFalse(token.isEmpty());

    }

    @Test
    void shouldextractEmail() {

        String email = "teste@gemail.com";

        String token = jwtService.generateToken(email);

        String extractedEmail = jwtService.extractEmail(token);

        assertEquals(email, extractedEmail);


    }

    @Test
    void soulThrowsExceptionWhenTokenIsInvalid() {

        String  invalidToken =
                "token.inavlido.aqui";

        assertThrows(
                JwtException.class,
                () -> jwtService.extractEmail(invalidToken)
        );

    }
}