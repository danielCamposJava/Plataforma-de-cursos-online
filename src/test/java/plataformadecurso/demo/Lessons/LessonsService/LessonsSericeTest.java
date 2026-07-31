package plataformadecurso.demo.Lessons.LessonsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import plataformadecurso.demo.Lessons.DTO.LessonsRequestDTO;
import plataformadecurso.demo.Lessons.DTO.LessonsResponseDTO;
import plataformadecurso.demo.Lessons.EntityLessons.LessonsEntity;
import plataformadecurso.demo.Lessons.LessonsRepository.LessonsRepository;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LessonsSericeTest {

    @Mock
    private LessonsRepository lessonsRepository;

    @InjectMocks
    private  LessonsSerice lessonsSerice;

    private LessonsEntity lessonsEntity;
    private UUID lessonId;


    @BeforeEach
    void setUp() {

        lessonId = UUID.randomUUID();

        lessonsEntity = new LessonsEntity();
        lessonsEntity.setId(lessonId);
        lessonsEntity.setTitle("lessons 1");
        lessonsEntity.setOrderIndex(1);

    }


    @Test
    void listAllLessons() {

        when( lessonsRepository.findAll() ).thenReturn(Collections.singletonList(lessonsEntity));

        List<LessonsResponseDTO> result = lessonsSerice.listAllLessons();

        assertNotNull(result);

        assertEquals(1, result.size());
        assertEquals(1, lessonsEntity.getOrderIndex());

        verify(lessonsRepository).findAll();
    }

    @Test
    void createLessons() {

        LessonsRequestDTO  requestDTO = new LessonsRequestDTO("lessons1", "test","sad", 1, 1);

        when( lessonsRepository.save(any(LessonsEntity.class))).thenReturn(lessonsEntity);

        assertNotNull(lessonsSerice.createLessons(requestDTO));

        assertEquals(1,requestDTO.orderIndex());

        verify(lessonsRepository).save(any(LessonsEntity.class));


    }

    @Test
    void updateLessons() {

        LessonsRequestDTO requestDTO = new LessonsRequestDTO("le333ssons1", "tes33t","sa33d", 2, 2);

        when(lessonsRepository.save(any(LessonsEntity.class))).thenReturn(lessonsEntity);

        when(lessonsRepository.findById(any(UUID.class))).thenReturn(Optional.of(lessonsEntity));

        LessonsResponseDTO result = lessonsSerice.updateLessons(lessonId, requestDTO);

        assertNotNull(result);
        assertEquals(1,requestDTO.orderIndex());

        verify(lessonsRepository, times( 2)).findById(any(UUID.class));
        verify(lessonsRepository).save(any(LessonsEntity.class));

    }

    @Test
    void deleteLessons() {

        when(lessonsRepository.findById(any(UUID.class))).thenReturn(Optional.of(lessonsEntity));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> lessonsSerice.deleteLessons(lessonId));

        assertEquals("LessonsEntity not found", exception.getMessage());

        verify(lessonsRepository).deleteById(any(UUID.class));

    }
}