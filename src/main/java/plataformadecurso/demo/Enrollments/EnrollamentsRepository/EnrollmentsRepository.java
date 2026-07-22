package plataformadecurso.demo.Enrollments.EnrollamentsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import plataformadecurso.demo.Enrollments.EnrollamentsEntity.EnrollmentEntity;
import java.util.UUID;

public interface EnrollmentsRepository
        extends JpaRepository<EnrollmentEntity, UUID> {
}