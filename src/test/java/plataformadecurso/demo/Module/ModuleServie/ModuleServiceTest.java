package plataformadecurso.demo.Module.ModuleServie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import plataformadecurso.demo.Module.DTO.ModuleResponseDTO;
import plataformadecurso.demo.Module.DTO.RequestModuloDTO;
import plataformadecurso.demo.Module.ModuleEntity.ModuleEntity;
import plataformadecurso.demo.Module.ModuleRepository.ModuleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ModuleServiceTest {

    @Mock
    private ModuleRepository moduleRepository;

    @InjectMocks
    private ModuleService moduleService;

    private ModuleEntity moduleEntity;
    private UUID moduleId;


    @BeforeEach
    void setUp() {

        moduleId = UUID.randomUUID();

        moduleEntity = new ModuleEntity();
        moduleEntity.setId(UUID.randomUUID());
        moduleEntity.setTitle("Module 1");
        moduleEntity.setOrderIndex(1);

    }

    @AfterEach
    void shouldFindAllModules() {

        when(moduleRepository.findAll()).thenReturn(
                List.of(moduleEntity)
        );

        List<ModuleResponseDTO> responseDTOs = moduleService.findAll();

        assertNotNull(responseDTOs);
        assertEquals(1, responseDTOs.size());
        assertEquals(  "Module 1", responseDTOs.getFirst().title());

        verify(moduleRepository).findAll();

    }

    @Test
    void shoulCreateModule() {

        RequestModuloDTO requestModuloDTO = new RequestModuloDTO("Module 1",2);

        when(moduleRepository.save(any(ModuleEntity.class))).thenReturn(moduleEntity);

        ModuleResponseDTO responseDTO = moduleService.CreateModule(requestModuloDTO);

        assertNotNull(responseDTO);
        assertEquals("Module 1", responseDTO.title());
        assertEquals(2, responseDTO.orderIndex());

        verify(moduleRepository).save(any(ModuleEntity.class));
    }

    @Test
    void shouldUpdateModule() {

        RequestModuloDTO requestModuloDTO = new RequestModuloDTO("Module 1",2);

        when(moduleRepository.save(any(ModuleEntity.class))).thenReturn(moduleEntity);

        when(moduleRepository.findById(any(UUID.class))).thenReturn(Optional.of(moduleEntity));

        ModuleResponseDTO responseDTO = moduleService.UpdateModule(moduleId, requestModuloDTO);

        assertEquals( "Module 1", responseDTO.title());
        assertEquals(3, responseDTO.orderIndex());

        verify(moduleRepository, times(2)).findById(any(UUID.class));

        verify(moduleRepository).save(any(ModuleEntity.class));

    }

    @Test
    void shouldDeleteModule(){

        when(moduleRepository.existsById(any(UUID.class))).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> moduleService.DeleteModule(moduleId));

        assertEquals("Module 1 not found", exception.getMessage());verify(moduleRepository).existsById(any(UUID.class));

        verify(moduleRepository , never()).deleteById(any(UUID.class));
    }
}