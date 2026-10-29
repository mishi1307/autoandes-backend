package pe.edu.upeu.autoandes.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.autoandes.dto.*;
import pe.edu.upeu.autoandes.entity.EstadoOrden;
import pe.edu.upeu.autoandes.service.service.OrdenServicioService;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/ordenes")
@RequiredArgsConstructor
public class OrdenServicioController {
    private final OrdenServicioService service;

    @PostMapping
    @PreAuthorize("hasAuthority('ORDEN_REGISTRAR')")
    public ResponseEntity<OrdenResponseDTO> crear(
            @Valid @RequestBody OrdenRequestDTO dto, Authentication auth) {
        OrdenResponseDTO r = service.crear(dto, auth.getName());
        return ResponseEntity.created(URI.create("/api/v1/ordenes/" + r.getId())).body(r);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ORDEN_LEER')")
    public List<OrdenResumenResponseDTO> buscar(
            @RequestParam(required = false) String placa,
            @RequestParam(required = false) EstadoOrden estado,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate hasta) {
        return service.buscar(placa, estado, desde, hasta);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ORDEN_LEER')")
    public OrdenResponseDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PatchMapping("/{id}/anular")
    @PreAuthorize("hasAuthority('ORDEN_ANULAR')")
    public OrdenResponseDTO anular(
            @PathVariable Long id, @Valid @RequestBody AnularOrdenRequestDTO dto) {
        return service.anular(id, dto.getMotivo());
    }
}
