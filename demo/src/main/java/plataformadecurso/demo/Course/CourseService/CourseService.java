package plataformadecurso.demo.Course.CourseService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import plataformadecurso.demo.Course.CourseEntity.CourseEntity;
import plataformadecurso.demo.Course.CourseRepository.CourseRepository;
import plataformadecurso.demo.Course.DTO.CourseRequestDTO;
import plataformadecurso.demo.Course.DTO.CourseResponseDTO;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;



    public List<CourseResponseDTO> getAllCourses(){
        return courseRepository.findAll().stream().map(
                CourseResponseDTO ::fromEntity
        ).toList();
    }

    public CourseResponseDTO  createCourses( CourseRequestDTO courseResponseDTO){
        CourseEntity courseEntity = new CourseEntity();

        courseEntity.setId(UUID.randomUUID());
        courseEntity.setName(courseResponseDTO.name());
        courseEntity.setDescription(courseResponseDTO.description());
        courseEntity.setImage(courseResponseDTO.image());
        courseEntity.setActor(courseResponseDTO.actor());

        CourseEntity savedCourseEntity = courseRepository.save(courseEntity);
        return CourseResponseDTO.fromEntity(savedCourseEntity);

    }

    public CourseResponseDTO  updateCourses(UUID id,  CourseRequestDTO courseResponseDTO){

        courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course with id: " + id + " not found")
        );

        CourseEntity courseEntity = courseRepository.findById(id).orElseThrow();
        courseEntity.setName(courseResponseDTO.name());
        courseEntity.setDescription(courseResponseDTO.description());
        courseEntity.setImage(courseResponseDTO.image());
        courseEntity.setActor(courseResponseDTO.actor());

        CourseEntity savedCourseEntity = courseRepository.save(courseEntity);
        return CourseResponseDTO.fromEntity(savedCourseEntity);

    }

    public  void deleteCourses( UUID id ){
        courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course with id: " + id + " not found")
        );
    }
}
