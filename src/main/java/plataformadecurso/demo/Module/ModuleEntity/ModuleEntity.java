package plataformadecurso.demo.Module.ModuleEntity;


import jakarta.persistence.*;
import lombok.*;
import plataformadecurso.demo.Course.CourseEntity.CourseEntity;
import plataformadecurso.demo.Lessons.EntityLessons.LessonsEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "modules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private Integer orderIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @OneToMany(
            mappedBy = "module",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<LessonsEntity> lessons = new ArrayList<>();
}


