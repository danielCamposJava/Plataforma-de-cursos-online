package plataformadecurso.demo.Lessons.LessonsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import plataformadecurso.demo.Lessons.EntityLessons.LessonsEntity;

import java.util.UUID;

public interface LessonsRepository extends JpaRepository<LessonsEntity , UUID> {
}
