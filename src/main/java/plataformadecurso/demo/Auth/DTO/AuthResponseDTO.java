package plataformadecurso.demo.Auth.DTO;

public class AuthResponseDTO {

    public String token;

    public AuthResponseDTO(String token) {

        this.token = token;
    }

    public String token() {
        token = token.toUpperCase();
        return token;
    }
}
