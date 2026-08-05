package plataformadecurso.demo.Auth.DTO;

public class AuthRequestDTO {

  public String email;
  String password;

    public AuthRequestDTO(String mail, String number) {
      this.email = mail;
      this.password = number;
    }


    public String email() {
    return email;
  }
  public String password() {
    return password;
  }
}
