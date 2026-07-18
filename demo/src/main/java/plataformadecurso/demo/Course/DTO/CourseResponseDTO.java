package plataformadecurso.demo.Course.DTO;

import plataformadecurso.demo.Course.CourseEntity.CourseEntity;

import java.util.UUID;

public record CourseResponseDTO(
        UUID  id,
        String name ,
        String descriptio,
        String image,
        String actor
) {
    public  static CourseResponseDTO fromEntity(CourseEntity entity) {
         return  new CourseResponseDTO(
                 entity.getId(),
                 entity.getName(),
                 entity.getDescription(),
                 entity.getImage(),
                 entity.getActor()
         );
    }
}
