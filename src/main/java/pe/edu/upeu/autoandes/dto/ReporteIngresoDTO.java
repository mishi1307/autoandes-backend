package pe.edu.upeu.autoandes.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteIngresoDTO {
    private String codigo;
    private String servicio;
    private Long cantidad;
    private BigDecimal ingreso;
}
