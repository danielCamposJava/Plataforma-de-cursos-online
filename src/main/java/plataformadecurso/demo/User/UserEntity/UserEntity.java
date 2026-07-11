package plataformadecurso.demo.User.UserEntity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import plataformadecurso.demo.Course.CourseEntity.CourseEntity;
import plataformadecurso.demo.Enrollments.EnrollmentsEntity.Enrollment;

import javax.management.relation.Role;
import java.util.List;
import java.util.UUID;

// test de commit
@Entity
@Table(name = "users")
@Getter
@Setter
//  Remove lixo técnico do Hibernate e garante que o Jackson veja os métodos da interface
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore //  NUNCA mostre a senha no GET do carrinho por segurança
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String zip;

    @OneToMany(mappedBy = "User")
    private List<Enrollment> enrollments;

    public  UserEntity(){}

    public  UserEntity( String  name, String email , String password, Role role,
      String phone , String address, String city,
                        String state , String country, String zip

    ){
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.city = city;
        this.address = address;
        this.country = country;
    }



}
