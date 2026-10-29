package pe.edu.upeu.autoandes.service.service;
import pe.edu.upeu.autoandes.dto.*; import java.util.List;
public interface VehiculoService { List<VehiculoResponseDTO> listar(Long clienteId); VehiculoResponseDTO obtener(Long id); VehiculoResponseDTO crear(VehiculoRequestDTO dto); void actualizar(Long id,VehiculoRequestDTO dto); void eliminar(Long id); }
