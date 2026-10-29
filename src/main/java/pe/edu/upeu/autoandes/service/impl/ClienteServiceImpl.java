package pe.edu.upeu.autoandes.service.impl;
import lombok.RequiredArgsConstructor; import pe.edu.upeu.autoandes.dto.ClienteResponseDTO; import pe.edu.upeu.autoandes.mapper.AutoAndesMapper; import pe.edu.upeu.autoandes.repository.ClienteRepository; import pe.edu.upeu.autoandes.service.service.ClienteService; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.List;
@Service @RequiredArgsConstructor @Transactional(readOnly=true)
public class ClienteServiceImpl implements ClienteService { private final ClienteRepository repo; public List<ClienteResponseDTO> listar(){return repo.findAll().stream().map(AutoAndesMapper::cliente).toList();} }
