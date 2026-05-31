package com.proyectoFinal.rutaSalvaje.mapper;

import com.proyectoFinal.rutaSalvaje.dto.ReservaRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.ReservaResponseDTO;
import com.proyectoFinal.rutaSalvaje.model.Plan;
import com.proyectoFinal.rutaSalvaje.model.Reserva;
import com.proyectoFinal.rutaSalvaje.model.Usuario;

public class ReservaMapper {

    public static Reserva aEntidad(ReservaRequestDTO dto, Usuario usuario, Plan plan) {

        if (dto == null || usuario == null || plan == null) {
            return null;
        }

        Reserva reserva = new Reserva();

        reserva.setCantidadPersonas(dto.getCantidadPersonas());
        reserva.setFecha(dto.getFecha());
        reserva.setUsuario(usuario);
        reserva.setPlan(plan);

        return reserva;
    }

    public static ReservaResponseDTO aDTO(Reserva reserva) {

        if (reserva == null) {
            return null;
        }

        ReservaResponseDTO dto = new ReservaResponseDTO();

        dto.setId(reserva.getId());
        dto.setCantidadPersonas(reserva.getCantidadPersonas());
        dto.setTotal(reserva.getTotal());
        dto.setFecha(reserva.getFecha());
        dto.setFechaCreacion(reserva.getFechaCreacion());
        dto.setEstado(reserva.getEstado());
        dto.setNombreUsuario(reserva.getUsuario().getNombre());
        dto.setNombrePlan(reserva.getPlan().getNombre());

        return dto;
    }
}
