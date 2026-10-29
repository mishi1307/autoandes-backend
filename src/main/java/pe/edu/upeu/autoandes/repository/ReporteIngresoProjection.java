package pe.edu.upeu.autoandes.repository;

import java.math.BigDecimal;

public interface ReporteIngresoProjection {
    String getCodigo();

    String getServicio();

    Long getCantidad();

    BigDecimal getIngreso();
}
