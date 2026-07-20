package plataformadecurso.demo.Lessons.DTO;

public record LessonsRequestDTO(
        String title,
        String description,
        String videoURL,
        Integer durationMinutes,
        Integer orderIndex
) {
}
