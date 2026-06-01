package com.proyectoFinal.rutaSalvaje.controller;


import com.proyectoFinal.rutaSalvaje.dto.ReservaRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.ReservaResponseDTO;
import com.proyectoFinal.rutaSalvaje.model.Rol;
import com.proyectoFinal.rutaSalvaje.model.Usuario;
import com.proyectoFinal.rutaSalvaje.service.ReservaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservas")
@SecurityRequirement(name = "bearerAuth")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> crearReserva(@Valid @RequestBody ReservaRequestDTO reserva) {
        ReservaResponseDTO reservaCreada = reservaService.crearReserva(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaCreada);
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listarReservas() {
        List<ReservaResponseDTO> reservas = reservaService.listarReservas();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ReservaResponseDTO>> listarReservasPorUsuario(@PathVariable Long usuarioId, Authentication authentication) {
        validarUsuarioAutenticado(usuarioId, authentication);
        List<ReservaResponseDTO> reservas = reservaService.listarReservasPorUsuario(usuarioId);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> buscarReserva(@PathVariable Long id) {
        ReservaResponseDTO reserva = reservaService.buscarReserva(id);
        return ResponseEntity.ok(reserva);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cancelar-usuario")
    public ResponseEntity<Void> cancelarReservaUsuario(@PathVariable Long id, @RequestParam Long usuarioId, Authentication authentication) {
        validarUsuarioAutenticado(usuarioId, authentication);
        reservaService.cancelarReservaUsuario(id, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> actualizarReserva(@PathVariable Long id, @Valid @RequestBody ReservaRequestDTO reserva) {
        ReservaResponseDTO actualizada = reservaService.actualizarReserva(id, reserva);
        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<Map<String, Boolean>> validarDisponibilidad(
            @RequestParam Long planId,
            @RequestParam String fecha) {
        boolean disponible = reservaService.estaDisponible(planId, LocalDateTime.parse(fecha));
        return ResponseEntity.ok(Map.of("disponible", disponible));
    }

    private void validarUsuarioAutenticado(Long usuarioId, Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        if (usuario.getRol() == Rol.ADMIN) {
            return;
        }

        if (!usuario.getId().equals(usuarioId)) {
            throw new IllegalArgumentException("No puedes consultar reservas de otro usuario");
        }
    }
}
