package plataformadecurso.demo.Course.CourseService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import plataformadecurso.demo.Course.CourseEntity.CourseEntity;
import plataformadecurso.demo.Course.CourseRepository.CourseRepository;
import plataformadecurso.demo.Course.DTO.CourseRequestDTO;
import plataformadecurso.demo.Course.DTO.CourseResponseDTO;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import static org.mockito.ArgumentMatchers.any;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;
    private UUID courseId ;

    @Mock
    private CourseEntity  courseEntity;

    @Test
    void setUp(){

        courseId = UUID.randomUUID();

        courseEntity = new CourseEntity();
        courseEntity.setId(courseId);
        courseEntity.setName("Course Name");
        courseEntity.setDescription("Course Description");

    }


    @Test
    public void shouldCreateCourse() {

        CourseRequestDTO  requestDTO  = new CourseRequestDTO("java spring", "curso do joven tranquilao", "jres", "tranquilao");

        when( courseRepository.save(any(CourseEntity.class))).thenReturn(courseEntity);

        assertNotEquals( courseService.createCourses(requestDTO), courseService.createCourses(requestDTO));

        assertEquals(courseId, courseEntity.getId());

        assertEquals("java spring", courseEntity.getName());
        assertEquals("curso do joven tranquilao", courseEntity.getDescription());
        assertEquals("jres", courseEntity.getDescription());
        assertEquals( "joven tranquilao", courseEntity.getDescription());

        verify(courseRepository).save(any(CourseEntity.class));

    }

    @Test
    public void shouldUpdateCourse() {

        CourseRequestDTO requestDTO = new CourseRequestDTO("phyton","curso de phyton","klam","guanabra");

        when( courseRepository.save(any(CourseEntity.class))).thenReturn(courseEntity);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));

        CourseResponseDTO result = courseService.updateCourses(courseId, requestDTO);

        assertNotNull(result);

        assertEquals("phython", courseEntity.getName());
        assertEquals("curso de phyton", courseEntity.getDescription());
        assertEquals("klam", courseEntity.getDescription());
        assertEquals("guanabra", courseEntity.getDescription());


        verify(courseRepository).save(any(CourseEntity.class));
    }


    @Test
    public void shouldeleteCourse() {

        when( courseRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> courseService.deleteCourses(courseId));

        assertEquals("Course not found", exception.getMessage());

        verify(courseRepository).deleteById(any(UUID.class));

    }
}
