package plataformadecurso.demo.Module.ModuleServie;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plataformadecurso.demo.Module.DTO.ModuleResponseDTO;
import plataformadecurso.demo.Module.DTO.RequestModuloDTO;
import plataformadecurso.demo.Module.ModuleEntity.ModuleEntity;
import plataformadecurso.demo.Module.ModuleRepository.ModuleRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ModuleService {

    private  final ModuleRepository moduleRepository;

    public List<ModuleResponseDTO>findAll(){
        return moduleRepository.findAll().stream().map(
                ModuleResponseDTO::fromEntity
        ).toList();
    }

    public ModuleResponseDTO CreateModule( ModuleResponseDTO requestModuloDTO){
        ModuleEntity moduleEntity = new ModuleEntity();

        moduleEntity.setId(UUID.randomUUID());
        moduleEntity.setTitle(requestModuloDTO.title());
        moduleEntity.setOrderIndex(requestModuloDTO.orderIndex());
        ModuleEntity savedModuleEntity = moduleRepository.save(moduleEntity);
        return ModuleResponseDTO.fromEntity(savedModuleEntity);
    }

    public ModuleResponseDTO UpdateModule( UUID id ,RequestModuloDTO requestModuloDTO){

        moduleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Module with id" + id + " not found")
        );
        ModuleEntity moduleEntity = moduleRepository.findById(id).orElseThrow();
        moduleEntity.setTitle(requestModuloDTO.title());
        moduleEntity.setOrderIndex(requestModuloDTO.orderIndex());
        moduleRepository.save(moduleEntity);
        return ModuleResponseDTO.fromEntity(moduleEntity);

    }

    public  void DeleteModule(UUID id){
        moduleRepository.deleteById(id);
    }
}
