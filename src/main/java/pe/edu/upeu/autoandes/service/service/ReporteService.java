package pe.edu.upeu.autoandes.service.service;
import pe.edu.upeu.autoandes.dto.ReporteIngresoDTO; import java.time.LocalDate; import java.util.List;
public interface ReporteService { List<ReporteIngresoDTO> ingresosPorServicio(LocalDate desde,LocalDate hasta); }
