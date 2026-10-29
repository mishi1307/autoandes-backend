package pe.edu.upeu.autoandes.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.autoandes.entity.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    boolean existsByPlacaIgnoreCase(String placa);

    List<Vehiculo> findByClienteId(Long clienteId);
}
