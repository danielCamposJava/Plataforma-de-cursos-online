package plataformadecurso.demo.Enrollments.EnrollamentsController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plataformadecurso.demo.Enrollments.DTO.RequesEnrollmentsDTO;
import plataformadecurso.demo.Enrollments.DTO.ResponseEnrollmentsDTO;
import plataformadecurso.demo.Enrollments.EnrollamentsService.EnrollmentsService;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EnrollmentsController {

    private  final EnrollmentsService enrollmentsService;

    @PostMapping
    public ResponseEntity<ResponseEnrollmentsDTO> createEnrollments(
            @RequestBody ResponseEnrollmentsDTO responseEnrollmentsDTO
    ){
        ResponseEnrollmentsDTO responseEnrollmentsDTO1 = enrollmentsService.createEnrollments(responseEnrollmentsDTO);
        return ResponseEntity.ok(responseEnrollmentsDTO1);
    }

    @GetMapping
    public ResponseEntity<List<ResponseEnrollmentsDTO>> getEnrollments(){
        return  ResponseEntity.ok(enrollmentsService.listAllResponses());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseEnrollmentsDTO> updateEnrollments(
            @PathVariable UUID uuid,
            @RequestBody RequesEnrollmentsDTO requesEnrollmentsDTO
            ){
        return ResponseEntity.ok(enrollmentsService.updateEnrollments( uuid, requesEnrollmentsDTO));
    }


    @DeleteMapping
    public  ResponseEntity<Void> deleteEnrollments(
            @PathVariable UUID uuid
    ){
        enrollmentsService.deleteEnrollments( uuid);
        return ResponseEntity.ok().build();
    }





}
