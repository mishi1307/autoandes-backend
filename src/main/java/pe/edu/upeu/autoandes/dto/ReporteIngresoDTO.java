package pe.edu.upeu.autoandes.dto;
import java.math.BigDecimal;
public record ReporteIngresoDTO(String codigo, String servicio, Long cantidad, BigDecimal ingreso) { }
