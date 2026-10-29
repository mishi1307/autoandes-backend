package pe.edu.upeu.autoandes.repository;
import pe.edu.upeu.autoandes.entity.Cliente; import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository<Cliente, Long> { }
