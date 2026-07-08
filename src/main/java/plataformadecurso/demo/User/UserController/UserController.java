package plataformadecurso.demo.User.UserController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plataformadecurso.demo.User.DTO.UserRequestDTO;
import plataformadecurso.demo.User.DTO.UserResponseDTO;
import plataformadecurso.demo.User.UserService.UserService;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

       private final UserService userService;

       @PostMapping
       public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO  userRequestDTO) {

           UserResponseDTO responseDTO = userService.createUser( userRequestDTO);

           return ResponseEntity.created( URI.create("/users/"+ responseDTO.id())).body(responseDTO);
       }

       @GetMapping
       public ResponseEntity<List<UserResponseDTO>> findAll() {
           return  ResponseEntity.ok(userService.findAllUser());
      }

      @PutMapping("/{ID}")
      public  ResponseEntity<UserResponseDTO> updateUser(
              @PathVariable UUID id,
              @RequestBody UserRequestDTO requestDTO
              ) {
           return  ResponseEntity.ok(userService.updateUser(id,requestDTO));
      }

      @DeleteMapping("/{id}")
      public  ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
           userService.deleteUser(id);
           return  ResponseEntity.noContent().build();
      }



}
