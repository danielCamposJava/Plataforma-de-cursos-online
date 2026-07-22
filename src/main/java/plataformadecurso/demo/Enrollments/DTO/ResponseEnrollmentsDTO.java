package plataformadecurso.demo.Enrollments.DTO;

import plataformadecurso.demo.Enrollments.EnrollamentsEntity.EnrollmentEntity;

import java.time.LocalDateTime;

public record ResponseEnrollmentsDTO(

        String course,
        String status,
        LocalDateTime enrolledAt,
        Double progress

) {

    public static ResponseEnrollmentsDTO fromEntity(EnrollmentEntity enrollmentEntity) {
        return new ResponseEnrollmentsDTO(
                enrollmentEntity.getCourse().getName(),
                enrollmentEntity.getStatus().name(),
                enrollmentEntity.getEnrolledAt(),
                enrollmentEntity.getProgress()
        );
    }
}