package pe.edu.upeu.autoandes.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.autoandes.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponseDTO> respuesta(
            HttpStatus s, String mensaje, HttpServletRequest r, Map<String, String> campos) {
        return ResponseEntity.status(s)
                .body(
                        new ErrorResponseDTO(
                                LocalDateTime.now(),
                                s.value(),
                                s.getReasonPhrase(),
                                mensaje,
                                r.getRequestURI(),
                                campos));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> validacion(
            MethodArgumentNotValidException e, HttpServletRequest r) {
        Map<String, String> campos = new LinkedHashMap<>();
        for (FieldError f : e.getBindingResult().getFieldErrors())
            campos.putIfAbsent(f.getField(), f.getDefaultMessage());
        return respuesta(HttpStatus.BAD_REQUEST, "Datos inválidos", r, campos);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> noEncontrado(
            RecursoNoEncontradoException e, HttpServletRequest r) {
        return respuesta(HttpStatus.NOT_FOUND, e.getMessage(), r, null);
    }

    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<ErrorResponseDTO> conflicto(
            ReglaNegocioException e, HttpServletRequest r) {
        return respuesta(HttpStatus.CONFLICT, e.getMessage(), r, null);
    }

    @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO> noAutorizado(
            org.springframework.security.core.AuthenticationException e, HttpServletRequest r) {
        return respuesta(
                HttpStatus.UNAUTHORIZED, "Credenciales inválidas o usuario inactivo", r, null);
    }

    @ExceptionHandler(SolicitudInvalidaException.class)
    public ResponseEntity<ErrorResponseDTO> solicitudInvalida(
            SolicitudInvalidaException e, HttpServletRequest r) {
        return respuesta(HttpStatus.BAD_REQUEST, e.getMessage(), r, null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> integridad(
            DataIntegrityViolationException e, HttpServletRequest r) {
        return respuesta(
                HttpStatus.CONFLICT,
                "La operación infringe una restricción de integridad",
                r,
                null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> inesperado(Exception e, HttpServletRequest r) {
        return respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado", r, null);
    }
}
