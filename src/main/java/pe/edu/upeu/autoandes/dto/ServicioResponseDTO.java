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
public class ServicioResponseDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private BigDecimal precio;
    private Integer duracionMin;
    private boolean activo;
}
