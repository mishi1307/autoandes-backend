package pe.edu.upeu.autoandes.dto;
import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.List;
public record OrdenResponseDTO(Long id, String numero, LocalDateTime fecha, String placa, String marca, String modelo, String cliente, Long kilometraje, String estado, BigDecimal total, String usuarioRegistro, String motivoAnulacion, List<DetalleOrdenResponseDTO> detalles) { }
