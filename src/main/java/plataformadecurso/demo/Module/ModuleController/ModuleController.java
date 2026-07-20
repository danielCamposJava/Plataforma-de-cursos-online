package plataformadecurso.demo.Module.ModuleController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plataformadecurso.demo.Module.DTO.ModuleResponseDTO;
import plataformadecurso.demo.Module.DTO.RequestModuloDTO;
import plataformadecurso.demo.Module.ModuleServie.ModuleService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/module")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping
    public ResponseEntity<ModuleResponseDTO>createModule(
            @RequestBody ModuleResponseDTO moduleResponseDTO
    ){
        ModuleResponseDTO moduleResponseDTO1 = moduleService.CreateModule(moduleResponseDTO);
        return (ResponseEntity<ModuleResponseDTO>) ResponseEntity.created(URI.create("/module/"+ moduleResponseDTO1));
    }

    @GetMapping
    public ResponseEntity<List<ModuleResponseDTO>> getAllModules(){
        return  ResponseEntity.ok( moduleService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuleResponseDTO>updateModule(
            @PathVariable UUID uuid,
            @RequestBody RequestModuloDTO requestDTO
            ){
         return  ResponseEntity.ok(moduleService.UpdateModule(uuid,requestDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void>deleteModule(
            @PathVariable UUID uuid
    ){
        moduleService.DeleteModule(uuid);
        return ResponseEntity.ok().build();
    }

}
