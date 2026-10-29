package pe.edu.upeu.autoandes.security;
import lombok.RequiredArgsConstructor; import pe.edu.upeu.autoandes.entity.Usuario; import pe.edu.upeu.autoandes.repository.UsuarioRepository; import org.springframework.security.core.authority.SimpleGrantedAuthority; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service; import java.util.*;
@Service @RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService { private final UsuarioRepository repo; private final PermisosPorRol permisos;
 @Override public UserDetails loadUserByUsername(String username){Usuario u=repo.findByUsernameIgnoreCase(username).orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado")); Set<String> roles=new HashSet<>(); u.getRoles().forEach(r->roles.add(r.getNombre())); Set<String> auth=new HashSet<>(permisos.paraRoles(roles)); roles.forEach(r->auth.add("ROLE_"+r)); List<SimpleGrantedAuthority> authorities=auth.stream().map(SimpleGrantedAuthority::new).toList(); return User.withUsername(u.getUsername()).password(u.getPassword()).disabled(!u.isActivo()).authorities(authorities).build(); }
}
