package plataformadecurso.demo.Module.ModuleRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import plataformadecurso.demo.Module.ModuleEntity.ModuleEntity;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleEntity, UUID> {
}
