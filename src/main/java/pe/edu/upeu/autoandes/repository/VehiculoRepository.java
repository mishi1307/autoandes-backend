package pe.edu.upeu.autoandes.repository;
import pe.edu.upeu.autoandes.entity.Vehiculo; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> { boolean existsByPlacaIgnoreCase(String placa); List<Vehiculo> findByClienteId(Long clienteId); }
