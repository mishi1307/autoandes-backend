package pe.edu.upeu.autoandes.service.service;

import java.time.LocalDate;
import java.util.List;
import pe.edu.upeu.autoandes.dto.ReporteIngresoDTO;

public interface ReporteService {
    List<ReporteIngresoDTO> ingresosPorServicio(LocalDate desde, LocalDate hasta);
}
