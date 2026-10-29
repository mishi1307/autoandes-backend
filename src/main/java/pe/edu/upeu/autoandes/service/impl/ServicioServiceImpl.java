package pe.edu.upeu.autoandes.service.impl;

import java.util.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.autoandes.dto.*;
import pe.edu.upeu.autoandes.entity.Servicio;
import pe.edu.upeu.autoandes.exception.*;
import pe.edu.upeu.autoandes.mapper.AutoAndesMapper;
import pe.edu.upeu.autoandes.repository.*;
import pe.edu.upeu.autoandes.service.service.ServicioService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServicioServiceImpl implements ServicioService {
    private static final Logger log = LoggerFactory.getLogger(ServicioServiceImpl.class);
    private final ServicioRepository repo;
    private final DetalleOrdenRepository detalleRepo;

    public List<ServicioResponseDTO> listar() {
        return repo.findAll().stream().map(AutoAndesMapper::servicio).toList();
    }

    public ServicioResponseDTO obtener(Long id) {
        return AutoAndesMapper.servicio(
                repo.findById(id)
                        .orElseThrow(
                                () ->
                                        new RecursoNoEncontradoException(
                                                "Servicio no encontrado: " + id)));
    }

    @Transactional
    public ServicioResponseDTO crear(ServicioRequestDTO d) {
        if (repo.existsByCodigoIgnoreCase(d.getCodigo())) {
            log.warn("Regla RN-01: código de servicio duplicado {}", d.getCodigo());
            throw new ReglaNegocioException("El código de servicio ya existe");
        }
        Servicio s = new Servicio();
        asignar(s, d);
        Servicio saved = repo.save(s);
        log.info("Servicio creado id={}", saved.getId());
        return AutoAndesMapper.servicio(saved);
    }

    @Transactional
    public void actualizar(Long id, ServicioRequestDTO d) {
        Servicio s =
                repo.findById(id)
                        .orElseThrow(
                                () ->
                                        new RecursoNoEncontradoException(
                                                "Servicio no encontrado: " + id));
        if (repo.existsByCodigoIgnoreCase(d.getCodigo())
                && !s.getCodigo().equalsIgnoreCase(d.getCodigo()))
            throw new ReglaNegocioException("El código de servicio ya existe");
        asignar(s, d);
        log.info("Servicio actualizado id={}", id);
    }

    @Transactional
    public ServicioResponseDTO cambiarEstado(Long id, boolean activo) {
        Servicio s =
                repo.findById(id)
                        .orElseThrow(
                                () ->
                                        new RecursoNoEncontradoException(
                                                "Servicio no encontrado: " + id));
        s.setActivo(activo);
        log.info("Estado de servicio actualizado id={} activo={}", id, activo);
        return AutoAndesMapper.servicio(s);
    }

    @Transactional
    public void eliminar(Long id) {
        Servicio s =
                repo.findById(id)
                        .orElseThrow(
                                () ->
                                        new RecursoNoEncontradoException(
                                                "Servicio no encontrado: " + id));
        if (detalleRepo.existsByServicioId(id)) {
            log.warn("Regla RN-01: servicio {} está asociado a órdenes", id);
            throw new ReglaNegocioException(
                    "El servicio usado en una orden no se puede eliminar; puede darse de baja");
        }
        repo.delete(s);
        log.info("Servicio eliminado id={}", id);
    }

    private void asignar(Servicio s, ServicioRequestDTO d) {
        s.setCodigo(d.getCodigo().toUpperCase(Locale.ROOT));
        s.setNombre(d.getNombre().trim());
        s.setPrecio(d.getPrecio());
        s.setDuracionMin(d.getDuracionMin());
    }
}
