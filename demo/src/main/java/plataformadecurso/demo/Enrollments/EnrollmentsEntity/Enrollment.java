package plataformadecurso.demo.Enrollments.EnrollmentsEntity;

import jakarta.persistence.*;
import plataformadecurso.demo.Course.CourseEntity.CourseEntity;
import plataformadecurso.demo.User.UserEntity.UserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    private LocalDateTime enrollmentDate;
}