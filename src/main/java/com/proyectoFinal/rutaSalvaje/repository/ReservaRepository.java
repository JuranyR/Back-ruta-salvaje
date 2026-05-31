package com.proyectoFinal.rutaSalvaje.repository;

import com.proyectoFinal.rutaSalvaje.model.EstadoReserva;
import com.proyectoFinal.rutaSalvaje.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByPlanIdAndFechaAndEstadoNot(Long planId, LocalDateTime fecha, EstadoReserva estado);

    boolean existsByPlanIdAndFechaAndEstadoNotAndIdNot(Long planId, LocalDateTime fecha, EstadoReserva estado, Long id);

    List<Reserva> findByUsuarioIdOrderByFechaDesc(Long usuarioId);
}
