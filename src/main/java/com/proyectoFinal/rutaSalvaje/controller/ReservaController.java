package com.proyectoFinal.rutaSalvaje.controller;


import com.proyectoFinal.rutaSalvaje.dto.ReservaRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.ReservaResponseDTO;
import com.proyectoFinal.rutaSalvaje.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
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

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> actualizarReserva(@PathVariable Long id, @Valid @RequestBody ReservaRequestDTO reserva) {
        ReservaResponseDTO actualizada = reservaService.actualizarReserva(id, reserva);
        return ResponseEntity.ok(actualizada);
    }
}
