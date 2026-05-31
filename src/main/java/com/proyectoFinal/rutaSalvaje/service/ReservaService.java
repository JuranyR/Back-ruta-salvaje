package com.proyectoFinal.rutaSalvaje.service;

import com.proyectoFinal.rutaSalvaje.dto.ReservaRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.ReservaResponseDTO;
import com.proyectoFinal.rutaSalvaje.mapper.ReservaMapper;
import com.proyectoFinal.rutaSalvaje.model.EstadoReserva;
import com.proyectoFinal.rutaSalvaje.model.Plan;
import com.proyectoFinal.rutaSalvaje.model.Reserva;
import com.proyectoFinal.rutaSalvaje.model.Usuario;
import com.proyectoFinal.rutaSalvaje.repository.PlanRepository;
import com.proyectoFinal.rutaSalvaje.repository.ReservaRepository;
import com.proyectoFinal.rutaSalvaje.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanRepository planRepository;

    public ReservaService(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository, PlanRepository planRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.planRepository = planRepository;
    }

    public ReservaResponseDTO crearReserva(ReservaRequestDTO reservaDTO) {

        Usuario usuario = usuarioRepository.findById(reservaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        Plan plan = planRepository.findById(reservaDTO.getPlanId())
                .orElseThrow(() -> new RuntimeException("El plan no existe"));

        if (!plan.getEstado()) {
            throw new IllegalArgumentException("El plan no está disponible");
        }

        if (reservaDTO.getFecha().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha no puede ser en el pasado");
        }

        if (reservaDTO.getCantidadPersonas() <= 0) {
            throw new IllegalArgumentException("La cantidad de personas debe ser mayor a 0");
        }

        if (!estaDisponible(plan.getId(), reservaDTO.getFecha())) {
            throw new IllegalArgumentException("Este plan ya esta reservado para esa fecha y hora");
        }

        Double total = precioFinal(plan) * reservaDTO.getCantidadPersonas();

        Reserva reserva = ReservaMapper.aEntidad(reservaDTO, usuario, plan);
        reserva.setTotal(total);

        Reserva guardada = reservaRepository.save(reserva);
        return ReservaMapper.aDTO(guardada);
    }

    public List<ReservaResponseDTO> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return convertirLista(reservas);
    }

    public List<ReservaResponseDTO> listarReservasPorUsuario(Long usuarioId) {
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("El usuario no existe");
        }

        return convertirLista(reservaRepository.findByUsuarioIdOrderByFechaDesc(usuarioId));
    }

    private List<ReservaResponseDTO> convertirLista(List<Reserva> reservas) {
        List<ReservaResponseDTO> lista = new ArrayList<>();
        for (Reserva r : reservas) {
            lista.add(ReservaMapper.aDTO(r));
        }
        return lista;
    }

    public ReservaResponseDTO buscarReserva(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser menor a 0");
        }
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La reserva con ID " + id + " no existe"));
        return ReservaMapper.aDTO(reserva);
    }

    public void cancelarReserva(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser menor a 0");
        }

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La reserva con ID " + id + " no existe"));

        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new IllegalArgumentException("La reserva ya está cancelada");
        }

        if (reserva.getEstado() == EstadoReserva.AGENDADA) {
            throw new IllegalArgumentException("No se puede cancelar una reserva ya agendada");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);
        reservaRepository.save(reserva);
    }

    public void cancelarReservaUsuario(Long id, Long usuarioId) {
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La reserva con ID " + id + " no existe"));

        if (!reserva.getUsuario().getId().equals(usuarioId)) {
            throw new IllegalArgumentException("No puedes cancelar una reserva de otro usuario");
        }

        cancelarReserva(id);
    }

    public ReservaResponseDTO actualizarReserva(Long id, ReservaRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser menor a 0");
        }

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La reserva con ID " + id + " no existe"));

        if (reserva.getEstado() != EstadoReserva.PENDIENTE) {
            throw new IllegalArgumentException("Solo se puede modificar una reserva pendiente");
        }

        if (dto.getFecha().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha no puede ser en el pasado");
        }

        if (dto.getCantidadPersonas() <= 0) {
            throw new IllegalArgumentException("La cantidad de personas debe ser mayor a 0");
        }

        if (reservaRepository.existsByPlanIdAndFechaAndEstadoNotAndIdNot(
                reserva.getPlan().getId(),
                dto.getFecha(),
                EstadoReserva.CANCELADA,
                reserva.getId())) {
            throw new IllegalArgumentException("Este plan ya esta reservado para esa fecha y hora");
        }

        Double total = precioFinal(reserva.getPlan()) * dto.getCantidadPersonas();

        reserva.setFecha(dto.getFecha());
        reserva.setCantidadPersonas(dto.getCantidadPersonas());
        reserva.setTotal(total);

        Reserva actualizada = reservaRepository.save(reserva);
        return ReservaMapper.aDTO(actualizada);
    }

    public boolean estaDisponible(Long planId, LocalDateTime fecha) {
        if (planId == null || fecha == null) {
            throw new IllegalArgumentException("El plan y la fecha son obligatorios");
        }

        if (fecha.isBefore(LocalDateTime.now())) {
            return false;
        }

        return !reservaRepository.existsByPlanIdAndFechaAndEstadoNot(planId, fecha, EstadoReserva.CANCELADA);
    }

    private Double precioFinal(Plan plan) {
        Double descuento = plan.getDescuentoPorcentaje() == null ? 0.0 : plan.getDescuentoPorcentaje();
        return plan.getPrecio() - (plan.getPrecio() * descuento / 100.0);
    }

}
