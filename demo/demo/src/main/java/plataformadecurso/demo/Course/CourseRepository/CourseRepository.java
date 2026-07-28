package plataformadecurso.demo.Course.CourseRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import plataformadecurso.demo.Course.CourseEntity.CourseEntity;

import java.util.UUID;

public  interface CourseRepository extends JpaRepository<CourseEntity, UUID>{


}

