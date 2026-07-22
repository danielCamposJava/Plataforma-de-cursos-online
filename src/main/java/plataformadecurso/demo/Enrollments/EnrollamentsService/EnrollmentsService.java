package plataformadecurso.demo.Enrollments.EnrollamentsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import plataformadecurso.demo.Enrollments.DTO.RequesEnrollmentsDTO;
import plataformadecurso.demo.Enrollments.DTO.ResponseEnrollmentsDTO;
import plataformadecurso.demo.Enrollments.EnrollamentsEntity.EnrollmentEntity;
import plataformadecurso.demo.Enrollments.EnrollamentsRepository.EnrollmentsRepository;
import java.util.List;
import java.util.UUID;

@Component
@Service
@RequiredArgsConstructor
public class EnrollmentsService{

    private final EnrollmentsRepository enrollmentsRepository;

    public  List<ResponseEnrollmentsDTO> listAllResponses() {

        return enrollmentsRepository.findAll().stream().map(
                ResponseEnrollmentsDTO :: fromEntity
        ).toList();
    }

    public ResponseEnrollmentsDTO  createEnrollments(RequesEnrollmentsDTO enrollmentsDTO) {

        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.setCourse(enrollmentEntity.getCourse());
        enrollmentEntity.setProgress(enrollmentEntity.getProgress());
        enrollmentEntity.setEnrolledAt(enrollmentEntity.getEnrolledAt());

        enrollmentsRepository.save(enrollmentEntity);

        return ResponseEnrollmentsDTO.fromEntity(enrollmentEntity);

    }

    public ResponseEnrollmentsDTO  updateEnrollments(UUID id , RequesEnrollmentsDTO enrollmentsDTO) {

       enrollmentsRepository.findById(id).orElseThrow(
               () ->  new RuntimeException("Enrollments with id " + id + " not found")
       );

       EnrollmentEntity enrollmentEntity = enrollmentsRepository.findById(id).orElseThrow();

       enrollmentEntity.setCourse(enrollmentEntity.getCourse());
       enrollmentEntity.setProgress(enrollmentEntity.getProgress());
       enrollmentEntity.setEnrolledAt(enrollmentEntity.getEnrolledAt());

       enrollmentsRepository.save(enrollmentEntity);

       return ResponseEnrollmentsDTO.fromEntity(enrollmentEntity);

    }

    public ResponseEnrollmentsDTO  deleteEnrollments(UUID id) {

        if(!enrollmentsRepository.existsById(id)){
            throw new RuntimeException("Enrollments with id " + id + " not found");
        }

        enrollmentsRepository.deleteById(id);

        return ResponseEnrollmentsDTO.fromEntity(enrollmentsRepository.findById(id).get());

    }

}
