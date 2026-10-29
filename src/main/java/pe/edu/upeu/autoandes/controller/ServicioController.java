package pe.edu.upeu.autoandes.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.autoandes.dto.*;
import pe.edu.upeu.autoandes.service.service.ServicioService;

@RestController
@RequestMapping("/api/v1/servicios")
@RequiredArgsConstructor
public class ServicioController {
    private final ServicioService service;

    @GetMapping
    @PreAuthorize("hasAuthority('SERVICIO_LEER')")
    public List<ServicioResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SERVICIO_LEER')")
    public ServicioResponseDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SERVICIO_ESCRIBIR')")
    public ResponseEntity<ServicioResponseDTO> crear(@Valid @RequestBody ServicioRequestDTO dto) {
        ServicioResponseDTO r = service.crear(dto);
        return ResponseEntity.created(URI.create("/api/v1/servicios/" + r.getId())).body(r);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SERVICIO_ESCRIBIR')")
    public ResponseEntity<Void> actualizar(
            @PathVariable Long id, @Valid @RequestBody ServicioRequestDTO dto) {
        service.actualizar(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    @PreAuthorize("hasAuthority('SERVICIO_ESCRIBIR')")
    public ServicioResponseDTO estado(
            @PathVariable Long id, @Valid @RequestBody ServicioEstadoRequestDTO dto) {
        return service.cambiarEstado(id, dto.getActivo());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SERVICIO_ESCRIBIR')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
