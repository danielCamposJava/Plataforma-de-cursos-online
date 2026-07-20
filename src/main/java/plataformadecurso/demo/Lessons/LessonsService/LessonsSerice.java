package plataformadecurso.demo.Lessons.LessonsService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import plataformadecurso.demo.Lessons.DTO.LessonsRequestDTO;
import plataformadecurso.demo.Lessons.DTO.LessonsResponseDTO;
import plataformadecurso.demo.Lessons.EntityLessons.LessonsEntity;
import plataformadecurso.demo.Lessons.LessonsRepository.LessonsRepository;

import java.util.List;
import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class LessonsSerice {

    private final LessonsRepository lessonsRepository;

    public List<LessonsResponseDTO> listAllLessons(){
        return lessonsRepository.findAll().stream().map(
                LessonsResponseDTO::fromEntity
        ).toList();
    }

    public LessonsResponseDTO createLessons(LessonsRequestDTO lessonsResponseDTO){

        LessonsEntity lessonsEntity = new LessonsEntity();

        lessonsEntity.setId(UUID.randomUUID());
        lessonsEntity.setTitle(lessonsResponseDTO.title());
        lessonsEntity.setDescription(lessonsResponseDTO.description());
        lessonsEntity.setDurationMinutes(lessonsEntity.getDurationMinutes());
        lessonsEntity.setOrderIndex(lessonsEntity.getOrderIndex());

        LessonsEntity savedLessonsEntity = lessonsRepository.save(lessonsEntity);
        return  LessonsResponseDTO.fromEntity(savedLessonsEntity);
    }

    public LessonsResponseDTO updateLessons( UUID id ,LessonsResponseDTO lessonsResponseDTO){

        lessonsRepository.findById(id).orElseThrow(
                () -> new RuntimeException( "lessons with id " + id + "not found")
        );

        LessonsEntity lessonsEntity = lessonsRepository.findById(id).orElseThrow();
        lessonsEntity.setTitle(lessonsResponseDTO.title());
        lessonsEntity.setDescription(lessonsResponseDTO.description());
        lessonsEntity.setDurationMinutes(lessonsEntity.getDurationMinutes());
        lessonsEntity.setOrderIndex(lessonsEntity.getOrderIndex());

        LessonsEntity  savedLessonEntity = lessonsRepository.save(lessonsEntity);
        return LessonsResponseDTO.fromEntity(savedLessonEntity);
    }

    public void deleteLessons(UUID id){
        lessonsRepository.findById(id).orElseThrow(
                () -> new RuntimeException( "Lessons with id " + id + "not found")

        );
    }
}
