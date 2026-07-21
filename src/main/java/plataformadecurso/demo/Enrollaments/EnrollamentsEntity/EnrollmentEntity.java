package plataformadecurso.demo.Enrollaments.EnrollamentsEntity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import plataformadecurso.demo.User.UserEntity.UserEntity;
import plataformadecurso.demo.Course.CourseEntity.CourseEntity;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;
    private LocalDateTime enrolledAt;
    private Double progress;


}