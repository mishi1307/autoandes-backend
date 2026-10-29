package pe.edu.upeu.autoandes.controller;
import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import pe.edu.upeu.autoandes.dto.*; import pe.edu.upeu.autoandes.service.service.AuthService; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/auth") @RequiredArgsConstructor
public class AuthController { private final AuthService service; @PostMapping("/login") public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO dto){return service.login(dto);} @GetMapping("/me") public UsuarioActualDTO actual(Authentication auth){return service.actual(auth.getName());} }
