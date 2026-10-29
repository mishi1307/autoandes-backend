package pe.edu.upeu.autoandes.controller;
import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import pe.edu.upeu.autoandes.dto.*; import pe.edu.upeu.autoandes.service.service.VehiculoService; import org.springframework.http.*; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.net.URI; import java.util.List;
@RestController @RequestMapping("/api/v1/vehiculos") @RequiredArgsConstructor
public class VehiculoController { private final VehiculoService service;
 @GetMapping @PreAuthorize("hasAuthority('VEHICULO_LEER')") public List<VehiculoResponseDTO> listar(@RequestParam(required=false) Long clienteId){return service.listar(clienteId);}
 @GetMapping("/{id}") @PreAuthorize("hasAuthority('VEHICULO_LEER')") public VehiculoResponseDTO obtener(@PathVariable Long id){return service.obtener(id);}
 @PostMapping @PreAuthorize("hasAuthority('VEHICULO_ESCRIBIR')") public ResponseEntity<VehiculoResponseDTO> crear(@Valid @RequestBody VehiculoRequestDTO dto){VehiculoResponseDTO r=service.crear(dto);return ResponseEntity.created(URI.create("/api/v1/vehiculos/"+r.id())).body(r);}
 @PutMapping("/{id}") @PreAuthorize("hasAuthority('VEHICULO_ESCRIBIR')") public ResponseEntity<Void> actualizar(@PathVariable Long id,@Valid @RequestBody VehiculoRequestDTO dto){service.actualizar(id,dto);return ResponseEntity.noContent().build();}
 @DeleteMapping("/{id}") @PreAuthorize("hasAuthority('VEHICULO_ESCRIBIR')") public ResponseEntity<Void> eliminar(@PathVariable Long id){service.eliminar(id);return ResponseEntity.noContent().build();}
}
