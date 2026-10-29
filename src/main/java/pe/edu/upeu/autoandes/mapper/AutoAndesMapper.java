package pe.edu.upeu.autoandes.mapper;

import pe.edu.upeu.autoandes.dto.*;
import pe.edu.upeu.autoandes.entity.*;

public final class AutoAndesMapper {
    private AutoAndesMapper() {}

    public static ServicioResponseDTO servicio(Servicio s) {
        return new ServicioResponseDTO(
                s.getId(),
                s.getCodigo(),
                s.getNombre(),
                s.getPrecio(),
                s.getDuracionMin(),
                s.isActivo());
    }

    public static VehiculoResponseDTO vehiculo(Vehiculo v) {
        return new VehiculoResponseDTO(
                v.getId(),
                v.getPlaca(),
                v.getMarca(),
                v.getModelo(),
                v.getAnio(),
                v.getCliente().getId(),
                v.getCliente().getNombres());
    }

    public static ClienteResponseDTO cliente(Cliente c) {
        return new ClienteResponseDTO(c.getId(), c.getDocumento(), c.getNombres(), c.getTelefono());
    }
}
