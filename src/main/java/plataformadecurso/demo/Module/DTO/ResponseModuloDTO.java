package plataformadecurso.demo.Module.DTO;

import plataformadecurso.demo.Module.ModuleEntity.ModuleEntity;
import java.util.UUID;

public record ResponseModuloDTO (
         UUID id,
         String title,
         Integer orderIndex
){
    public static ResponseModuloDTO fromEntity(UUID id, ModuleEntity moduleEntity){
        return new  ResponseModuloDTO(
          moduleEntity.getId(),
          moduleEntity.getTitle(),
          moduleEntity.getOrderIndex()
        );
    }
}
