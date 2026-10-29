package pe.edu.upeu.autoandes.service.service;
import pe.edu.upeu.autoandes.dto.*; import java.util.List;
public interface ServicioService { List<ServicioResponseDTO> listar(); ServicioResponseDTO obtener(Long id); ServicioResponseDTO crear(ServicioRequestDTO dto); void actualizar(Long id,ServicioRequestDTO dto); ServicioResponseDTO cambiarEstado(Long id,boolean activo); void eliminar(Long id); }
