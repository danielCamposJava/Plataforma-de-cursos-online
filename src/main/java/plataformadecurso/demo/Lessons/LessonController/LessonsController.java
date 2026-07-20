package plataformadecurso.demo.Lessons.LessonController;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plataformadecurso.demo.Course.DTO.CourseRequestDTO;
import plataformadecurso.demo.Course.DTO.CourseResponseDTO;
import plataformadecurso.demo.Lessons.DTO.LessonsRequestDTO;
import plataformadecurso.demo.Lessons.DTO.LessonsResponseDTO;
import plataformadecurso.demo.Lessons.LessonsService.LessonsSerice;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonsController {

    private  final LessonsSerice lessonsSerice;

    @PostMapping
    public ResponseEntity<LessonsResponseDTO> createLessons(@RequestBody LessonsRequestDTO lessonsRequestDTO){

        LessonsResponseDTO lessonsResponseDTO = lessonsSerice.createLessons(lessonsRequestDTO);
        return ResponseEntity.created(URI.create("/lessons/"+ lessonsResponseDTO.id())).body(lessonsResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<LessonsResponseDTO>> getLessons(@RequestParam Integer id){
        return  ResponseEntity.ok(lessonsSerice.listAllLessons());
    }

    @PostMapping("/{id}")
    public  ResponseEntity<CourseResponseDTO> createCourse(@PathVariable UUID id, @RequestBody CourseRequestDTO courseRequestDTO){
        lessonsSerice.deleteLessons(UUID.randomUUID());
        return ResponseEntity.noContent().build();
    }
}
