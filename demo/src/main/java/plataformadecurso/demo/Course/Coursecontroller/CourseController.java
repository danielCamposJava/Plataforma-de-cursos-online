package plataformadecurso.demo.Course.Coursecontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plataformadecurso.demo.Course.CourseService.CourseService;
import plataformadecurso.demo.Course.DTO.CourseRequestDTO;
import plataformadecurso.demo.Course.DTO.CourseResponseDTO;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseRequestDTO){

        CourseResponseDTO  courseResponseDTO = courseService.createCourses(courseRequestDTO);
        return ResponseEntity.created( URI.create("/courses/"+ courseResponseDTO.id())).body(courseResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses(){
         return  ResponseEntity.ok( courseService.getAllCourses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable UUID id ,
            @RequestBody CourseRequestDTO requestDTO
    ){
        return  ResponseEntity.ok(courseService.updateCourses(id, requestDTO));
    }

    @DeleteMapping
   public ResponseEntity<Void> deleteCourse(@PathVariable UUID id){
        courseService.deleteCourses(id);
        return ResponseEntity.noContent().build();
    }

}
