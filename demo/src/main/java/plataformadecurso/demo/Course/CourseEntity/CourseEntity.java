package plataformadecurso.demo.Course.CourseEntity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import plataformadecurso.demo.User.UserEntity.UserEntity;

import java.util.List;
import java.util.UUID;

@Entity
@Table( name = "course")
@Getter
@Setter
public class CourseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String actor ;

    @ManyToMany(mappedBy = "course")
    private List<UserEntity> users;

    public  CourseEntity(){}

    public CourseEntity(String name, String description, String image, String actor) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.actor = actor;
    }
}
