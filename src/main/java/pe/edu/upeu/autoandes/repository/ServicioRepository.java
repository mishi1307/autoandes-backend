package pe.edu.upeu.autoandes.repository;
import pe.edu.upeu.autoandes.entity.Servicio; import org.springframework.data.jpa.repository.JpaRepository;
public interface ServicioRepository extends JpaRepository<Servicio, Long> { boolean existsByCodigoIgnoreCase(String codigo); boolean existsByIdAndActivoTrue(Long id); boolean existsByIdAndActivoFalse(Long id); }
