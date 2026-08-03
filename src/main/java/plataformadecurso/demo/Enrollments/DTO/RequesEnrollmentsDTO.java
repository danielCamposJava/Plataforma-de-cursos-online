package plataformadecurso.demo.Enrollments.DTO;

import java.time.LocalDateTime;

public record RequesEnrollmentsDTO(
        String course,
        String status,
        String enrollendAt,
        String progress
) {
    public LocalDateTime enrolledAt(){
        return LocalDateTime.now();
    }
}
