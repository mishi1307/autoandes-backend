package pe.edu.upeu.autoandes.dto;
import java.math.BigDecimal; import java.time.LocalDateTime;
public record OrdenResumenResponseDTO(Long id, String numero, LocalDateTime fecha, String placa, String cliente, Long kilometraje, String estado, BigDecimal total) { }
