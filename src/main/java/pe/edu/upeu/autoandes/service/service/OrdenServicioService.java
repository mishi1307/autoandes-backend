package pe.edu.upeu.autoandes.service.service;

import java.time.LocalDate;
import java.util.List;
import pe.edu.upeu.autoandes.dto.*;
import pe.edu.upeu.autoandes.entity.EstadoOrden;

public interface OrdenServicioService {
    OrdenResponseDTO crear(OrdenRequestDTO dto, String username);

    List<OrdenResumenResponseDTO> buscar(
            String placa, EstadoOrden estado, LocalDate desde, LocalDate hasta);

    OrdenResponseDTO obtener(Long id);

    OrdenResponseDTO anular(Long id, String motivo);
}
