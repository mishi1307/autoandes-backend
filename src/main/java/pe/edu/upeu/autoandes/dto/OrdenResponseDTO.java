package pe.edu.upeu.autoandes.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdenResponseDTO {
    private Long id;
    private String numero;
    private LocalDateTime fecha;
    private String placa;
    private String marca;
    private String modelo;
    private String cliente;
    private Long kilometraje;
    private String estado;
    private BigDecimal total;
    private String usuarioRegistro;
    private String motivoAnulacion;
    private List<DetalleOrdenResponseDTO> detalles;
}
