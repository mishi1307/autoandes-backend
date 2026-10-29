package pe.edu.upeu.autoandes.dto;
import java.util.Set;
public record LoginResponseDTO(String token,String tipo,long expiraEnSegundos,String username,String nombreCompleto,Set<String> roles,Set<String> permisos) { }
