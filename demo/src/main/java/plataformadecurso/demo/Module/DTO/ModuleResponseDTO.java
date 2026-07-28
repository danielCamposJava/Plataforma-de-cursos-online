package plataformadecurso.demo.Module.DTO;

import plataformadecurso.demo.Module.ModuleEntity.ModuleEntity;

import java.util.UUID;

public record ModuleResponseDTO(
        UUID id,
        String title,
        Integer orderIndex
) {

    public static ModuleResponseDTO fromEntity(ModuleEntity moduleEntity) {
      return new ModuleResponseDTO(
              moduleEntity.getId(),
              moduleEntity.getTitle(),
              moduleEntity.getOrderIndex()
      );
    }
}