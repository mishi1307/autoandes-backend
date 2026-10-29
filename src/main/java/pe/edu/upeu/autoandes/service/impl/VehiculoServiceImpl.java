package pe.edu.upeu.autoandes.service.impl;

import java.util.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.autoandes.dto.*;
import pe.edu.upeu.autoandes.entity.*;
import pe.edu.upeu.autoandes.exception.*;
import pe.edu.upeu.autoandes.mapper.AutoAndesMapper;
import pe.edu.upeu.autoandes.repository.*;
import pe.edu.upeu.autoandes.service.service.VehiculoService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VehiculoServiceImpl implements VehiculoService {
    private static final Logger log = LoggerFactory.getLogger(VehiculoServiceImpl.class);
    private final VehiculoRepository repo;
    private final ClienteRepository clienteRepo;
    private final OrdenServicioRepository ordenRepo;

    public List<VehiculoResponseDTO> listar(Long clienteId) {
        return (clienteId == null ? repo.findAll() : repo.findByClienteId(clienteId))
                .stream().map(AutoAndesMapper::vehiculo).toList();
    }

    public VehiculoResponseDTO obtener(Long id) {
        return AutoAndesMapper.vehiculo(
                repo.findById(id)
                        .orElseThrow(
                                () ->
                                        new RecursoNoEncontradoException(
                                                "Vehículo no encontrado: " + id)));
    }

    @Transactional
    public VehiculoResponseDTO crear(VehiculoRequestDTO d) {
        validarPlaca(d.getPlaca(), null);
        Cliente c =
                clienteRepo
                        .findById(d.getClienteId())
                        .orElseThrow(
                                () ->
                                        new RecursoNoEncontradoException(
                                                "Cliente no encontrado: " + d.getClienteId()));
        Vehiculo v = new Vehiculo();
        asignar(v, d, c);
        v = repo.save(v);
        log.info("Vehículo creado id={} placa={}", v.getId(), v.getPlaca());
        return AutoAndesMapper.vehiculo(v);
    }

    @Transactional
    public void actualizar(Long id, VehiculoRequestDTO d) {
        Vehiculo v =
                repo.findById(id)
                        .orElseThrow(
                                () ->
                                        new RecursoNoEncontradoException(
                                                "Vehículo no encontrado: " + id));
        validarPlaca(d.getPlaca(), id);
        Cliente c =
                clienteRepo
                        .findById(d.getClienteId())
                        .orElseThrow(
                                () ->
                                        new RecursoNoEncontradoException(
                                                "Cliente no encontrado: " + d.getClienteId()));
        asignar(v, d, c);
        log.info("Vehículo actualizado id={}", id);
    }

    @Transactional
    public void eliminar(Long id) {
        Vehiculo v =
                repo.findById(id)
                        .orElseThrow(
                                () ->
                                        new RecursoNoEncontradoException(
                                                "Vehículo no encontrado: " + id));
        if (ordenRepo.existsByVehiculoId(id)) {
            log.warn("Regla RN-02: vehículo {} tiene órdenes", id);
            throw new ReglaNegocioException(
                    "No se puede eliminar un vehículo con órdenes registradas");
        }
        repo.delete(v);
        log.info("Vehículo eliminado id={}", id);
    }

    private void validarPlaca(String placa, Long id) {
        if (repo.existsByPlacaIgnoreCase(placa)
                && (id == null
                        || !repo.findById(id)
                                .map(v -> v.getPlaca().equalsIgnoreCase(placa))
                                .orElse(false))) {
            log.warn("Regla RN-02: placa duplicada {}", placa);
            throw new ReglaNegocioException("La placa ya está registrada");
        }
    }

    private void asignar(Vehiculo v, VehiculoRequestDTO d, Cliente c) {
        v.setPlaca(d.getPlaca().toUpperCase(Locale.ROOT));
        v.setMarca(d.getMarca().trim());
        v.setModelo(d.getModelo().trim());
        v.setAnio(d.getAnio());
        v.setCliente(c);
    }
}
