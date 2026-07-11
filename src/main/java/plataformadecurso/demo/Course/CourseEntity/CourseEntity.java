package plataformadecurso.demo.Course.CourseEntity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "course")
@Getter
@Setter
public class CourseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String actor ;

    public  CourseEntity(){}

    public CourseEntity(String name, String description, String image, String actor) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.actor = actor;
    }
}
