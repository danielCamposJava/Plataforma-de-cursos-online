package plataformadecurso.demo.Enrollments.EnrollamentsService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import plataformadecurso.demo.Enrollments.DTO.RequesEnrollmentsDTO;
import plataformadecurso.demo.Enrollments.DTO.ResponseEnrollmentsDTO;
import plataformadecurso.demo.Enrollments.EnrollamentsEntity.EnrollmentEntity;
import plataformadecurso.demo.Enrollments.EnrollamentsRepository.EnrollmentsRepository;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentsService {

    private final EnrollmentsRepository enrollmentsRepository;

    public List<ResponseEnrollmentsDTO> listAllResponses() {

        return enrollmentsRepository.findAll()
                .stream()
                .map(ResponseEnrollmentsDTO::fromEntity)
                .toList();
    }

    public ResponseEnrollmentsDTO createEnrollments(RequesEnrollmentsDTO enrollmentsDTO) {

        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();

        enrollmentEntity.setCourse(enrollmentsDTO.course());
        enrollmentEntity.setProgress(Double.valueOf(enrollmentsDTO.progress()));
        enrollmentEntity.setEnrolledAt(enrollmentsDTO.enrolledAt());

        EnrollmentEntity savedEntity =
                enrollmentsRepository.save(enrollmentEntity);

        return ResponseEnrollmentsDTO.fromEntity(savedEntity);
    }

    public ResponseEnrollmentsDTO updateEnrollments(
            UUID id,
            RequesEnrollmentsDTO enrollmentsDTO) {

        EnrollmentEntity enrollmentEntity =
                enrollmentsRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Enrollment with id " + id + " not found"
                                ));

        enrollmentEntity.setCourse(enrollmentsDTO.course());
        enrollmentEntity.setProgress(Double.valueOf(enrollmentsDTO.progress()));
        enrollmentEntity.setEnrolledAt(enrollmentsDTO.enrolledAt());

        EnrollmentEntity updatedEntity =
                enrollmentsRepository.save(enrollmentEntity);

        return ResponseEnrollmentsDTO.fromEntity(updatedEntity);
    }

    public void deleteEnrollments(UUID id) {

        if (!enrollmentsRepository.existsById(id)) {
            throw new RuntimeException(
                    "Enrollment with id " + id + " not found"
            );
        }

        enrollmentsRepository.deleteById(id);
    }
}