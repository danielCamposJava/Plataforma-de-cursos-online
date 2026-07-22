package plataformadecurso.demo.Enrollments.EnrollamentsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import plataformadecurso.demo.Course.CourseService.CourseService;

public  interface EnllmentsRepository  extends JpaRepository<CourseService,Integer> {
}
