package pe.edu.upeu.autoandes.repository;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import pe.edu.upeu.autoandes.entity.*;

public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Long> {
    @Query(
            "select max(o.kilometraje) from OrdenServicio o where o.vehiculo.id=:vehiculoId and o.estado=pe.edu.upeu.autoandes.entity.EstadoOrden.REGISTRADA")
    Optional<Long> ultimoKilometraje(@Param("vehiculoId") Long vehiculoId);

    boolean existsByVehiculoId(Long vehiculoId);

    @Query(
            "select o from OrdenServicio o join fetch o.vehiculo v join fetch v.cliente where (:placa is null or upper(v.placa)=upper(:placa)) and (:estado is null or o.estado=:estado) and (:desde is null or o.fecha>=:desde) and (:hasta is null or o.fecha<:hasta) order by o.fecha desc")
    List<OrdenServicio> buscar(
            @Param("placa") String placa,
            @Param("estado") EstadoOrden estado,
            @Param("desde") LocalDateTime desde,
            @Param("hasta") LocalDateTime hasta);

    @Query(
            "select d.servicio.codigo as codigo, d.servicio.nombre as servicio, sum(d.cantidad) as cantidad, sum(d.subtotal) as ingreso from DetalleOrden d join d.orden o where o.estado=pe.edu.upeu.autoandes.entity.EstadoOrden.REGISTRADA and o.fecha>=:desde and o.fecha<:hasta group by d.servicio.codigo,d.servicio.nombre order by ingreso desc")
    List<ReporteIngresoProjection> reporte(
            @Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);
}
