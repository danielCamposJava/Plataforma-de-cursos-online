package plataformadecurso.demo.User.DTO;


public record UserRequestDTO(
        String name ,
        String email,
        String password,
        String phone,
        String address,
        String city,
        String  country,
        String zip
) { }
