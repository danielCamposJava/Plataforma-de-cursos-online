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
@RequestMapping("/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;


    @PostMapping
    public ResponseEntity<ModuleResponseDTO> createModule(
            @RequestBody RequestModuloDTO requestDTO
    ) {

        ModuleResponseDTO response =
                moduleService.CreateModule(requestDTO);

        URI uri = URI.create(
                "/modules/" + response.id()
        );

        return ResponseEntity
                .created(uri)
                .body(response);
    }


    @GetMapping
    public ResponseEntity<List<ModuleResponseDTO>> getAllModules(){

        return ResponseEntity.ok(
                moduleService.findAll()
        );
    }



    @PutMapping("/{id}")
    public ResponseEntity<ModuleResponseDTO> updateModule(
            @PathVariable UUID id,
            @RequestBody RequestModuloDTO requestDTO
    ){

        return ResponseEntity.ok(
                moduleService.UpdateModule(id, requestDTO)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(
            @PathVariable UUID id
    ){

        moduleService.DeleteModule(id);

        return ResponseEntity.noContent().build();
    }
}