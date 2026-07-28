package plataformadecurso.demo.Auth.AutController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plataformadecurso.demo.Auth.AuthService.AuthService;
import plataformadecurso.demo.Auth.DTO.AuthRequestDTO;
import plataformadecurso.demo.Auth.DTO.AuthResponseDTO;

@RestController
@RequestMapping("/Auth")
@RequiredArgsConstructor
public class AuthController {

 private final AuthService authService;

 @PostMapping("/login")
 public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO){
     return ResponseEntity.ok(authService.login(authRequestDTO));
 }
}
