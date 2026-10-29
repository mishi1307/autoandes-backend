package pe.edu.upeu.autoandes.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.autoandes.dto.ReporteIngresoDTO;
import pe.edu.upeu.autoandes.service.service.ReporteService;

@RestController
@RequestMapping("/api/v1/reportes")
@RequiredArgsConstructor
public class ReporteController {
    private final ReporteService service;

    @GetMapping("/ingresos-por-servicio")
    @PreAuthorize("hasAuthority('REPORTE_VER')")
    public List<ReporteIngresoDTO> ingresos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return service.ingresosPorServicio(desde, hasta);
    }
}
