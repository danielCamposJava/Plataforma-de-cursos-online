package plataformadecurso.demo.Module.ModuleServie;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plataformadecurso.demo.Module.ModuleRepository.ModuleRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ModuleService {

    private  final ModuleRepository moduleRepository;

}
