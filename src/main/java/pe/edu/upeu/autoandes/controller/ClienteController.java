package pe.edu.upeu.autoandes.controller;
import lombok.RequiredArgsConstructor; import pe.edu.upeu.autoandes.dto.ClienteResponseDTO; import pe.edu.upeu.autoandes.service.service.ClienteService; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/v1/clientes") @RequiredArgsConstructor
public class ClienteController { private final ClienteService service; @GetMapping @PreAuthorize("hasAuthority('VEHICULO_LEER')") public List<ClienteResponseDTO> listar(){return service.listar();} }
