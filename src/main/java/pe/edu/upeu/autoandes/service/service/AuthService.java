package pe.edu.upeu.autoandes.service.service;
import pe.edu.upeu.autoandes.dto.*;
public interface AuthService { LoginResponseDTO login(LoginRequestDTO request); UsuarioActualDTO actual(String username); }
