package plataformadecurso.demo.Module.ModuleEntity;


import jakarta.persistence.*;
import lombok.*;
import plataformadecurso.demo.Course.CourseEntity.CourseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="modules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ModuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JoinColumn( name = "course")
    private CourseEntity course;

    @OneToMany(
            mappedBy = "module",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<LessonEntity> lessons = new ArrayList<>();

}
