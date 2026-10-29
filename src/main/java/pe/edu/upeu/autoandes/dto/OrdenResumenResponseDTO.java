package pe.edu.upeu.autoandes.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdenResumenResponseDTO {
    private Long id;
    private String numero;
    private LocalDateTime fecha;
    private String placa;
    private String cliente;
    private Long kilometraje;
    private String estado;
    private BigDecimal total;
}
