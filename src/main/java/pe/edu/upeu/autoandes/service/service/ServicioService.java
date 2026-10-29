package pe.edu.upeu.autoandes.service.service;

import java.util.List;
import pe.edu.upeu.autoandes.dto.*;

public interface ServicioService {
    List<ServicioResponseDTO> listar();

    ServicioResponseDTO obtener(Long id);

    ServicioResponseDTO crear(ServicioRequestDTO dto);

    void actualizar(Long id, ServicioRequestDTO dto);

    ServicioResponseDTO cambiarEstado(Long id, boolean activo);

    void eliminar(Long id);
}
