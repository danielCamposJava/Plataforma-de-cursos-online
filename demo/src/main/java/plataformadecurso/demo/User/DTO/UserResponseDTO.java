package plataformadecurso.demo.User.DTO;
import plataformadecurso.demo.User.UserEntity.UserEntity;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String phone,
        String address,
        String city,
        String state,
        String country,
        String zip
) {
    public static UserResponseDTO fromEntity(UserEntity entity) {
        return new UserResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getZip()
        );
    }
}
