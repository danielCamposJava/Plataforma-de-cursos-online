package plataformadecurso.demo.Lessons.DTO;

import plataformadecurso.demo.Lessons.EntityLessons.LessonsEntity;
import java.util.UUID;

public record LessonsResponseDTO(
        UUID id,
        String title,
        String description,
        String videoURL,
        Integer durationMinutes,
        Integer orderIndex
) {
    public static  LessonsResponseDTO fromEntity(LessonsEntity lessonsEntities) {
        return new LessonsResponseDTO(
                lessonsEntities.getId(),
                lessonsEntities.getTitle(),
                lessonsEntities.getDescription(),
                lessonsEntities.getVideoUrl(),
                lessonsEntities.getDurationMinutes(),
                lessonsEntities.getOrderIndex()
        );
    }

}
