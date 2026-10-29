package pe.edu.upeu.autoandes.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdenRequestDTO {
    @NotNull @Positive private Long vehiculoId;
    @NotNull @PositiveOrZero private Long kilometraje;

    @NotEmpty
    @Size(max = 10)
    private List<@Valid DetalleOrdenRequestDTO> detalles;
}
