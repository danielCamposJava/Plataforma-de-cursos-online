package plataformadecurso.demo.User.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import plataformadecurso.demo.User.UserEntity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public  interface UserRepository extends JpaRepository<UserEntity, UUID> {

  Optional<UserEntity> findById(UUID id);
  boolean existsById(UUID id);
}
