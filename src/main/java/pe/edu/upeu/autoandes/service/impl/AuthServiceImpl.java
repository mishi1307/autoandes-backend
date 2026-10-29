package pe.edu.upeu.autoandes.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;
import pe.edu.upeu.autoandes.dto.*;
import pe.edu.upeu.autoandes.entity.Usuario;
import pe.edu.upeu.autoandes.exception.RecursoNoEncontradoException;
import pe.edu.upeu.autoandes.repository.UsuarioRepository;
import pe.edu.upeu.autoandes.security.*;
import pe.edu.upeu.autoandes.service.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager manager;
    private final UsuarioRepository users;
    private final JwtService jwt;
    private final PermisosPorRol permisos;

    public LoginResponseDTO login(LoginRequestDTO r) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(r.getUsername(), r.getPassword()));
        Usuario u =
                users.findByUsernameIgnoreCase(r.getUsername())
                        .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));
        Set<String> roles =
                u.getRoles().stream()
                        .map(x -> x.getNombre())
                        .collect(Collectors.toCollection(TreeSet::new));
        Set<String> perms = permisos.paraRoles(roles);
        return new LoginResponseDTO(
                jwt.generar(u.getUsername(), roles, perms),
                "Bearer",
                jwt.expiracionSegundos(),
                u.getUsername(),
                u.getNombreCompleto(),
                roles,
                perms);
    }

    public UsuarioActualDTO actual(String username) {
        Usuario u =
                users.findByUsernameIgnoreCase(username)
                        .orElseThrow(
                                () -> new RecursoNoEncontradoException("Usuario no encontrado"));
        Set<String> roles =
                u.getRoles().stream()
                        .map(x -> x.getNombre())
                        .collect(Collectors.toCollection(TreeSet::new));
        return new UsuarioActualDTO(
                u.getUsername(), u.getNombreCompleto(), roles, permisos.paraRoles(roles));
    }
}
