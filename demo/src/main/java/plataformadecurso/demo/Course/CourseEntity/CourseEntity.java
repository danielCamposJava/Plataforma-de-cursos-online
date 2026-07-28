package plataformadecurso.demo.Course.CourseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import plataformadecurso.demo.Module.ModuleEntity.ModuleEntity;
import plataformadecurso.demo.User.UserEntity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 2000)
    private String description;

    private String image;

    @Column(nullable = false)
    private String actor ;

    @ManyToMany(mappedBy = "courses")
    private List<UserEntity> users;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<ModuleEntity> modules = new ArrayList<>();


    public CourseEntity() {
    }

    public CourseEntity(String name, String description, String image, String author) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.actor = actor;
    }
}