package com.proyectoFinal.rutaSalvaje.mapper;

import com.proyectoFinal.rutaSalvaje.dto.ResenaRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.ResenaResponseDTO;
import com.proyectoFinal.rutaSalvaje.model.Plan;
import com.proyectoFinal.rutaSalvaje.model.Resena;
import com.proyectoFinal.rutaSalvaje.model.Usuario;

public class ResenaMapper {

    public static Resena aEntidad (ResenaRequestDTO dto, Usuario usuario, Plan plan) {

        if (dto == null || usuario == null || plan == null) {
            return null;
        }

        Resena resena = new Resena();

        resena.setCalificacion(dto.getCalificacion());
        resena.setComentario(dto.getComentario());
        resena.setUsuario(usuario);
        resena.setPlan(plan);

        return resena;
    }

    public static ResenaResponseDTO aDTO(Resena resena) {

        if (resena == null) {
            return null;
        }

        ResenaResponseDTO dto = new ResenaResponseDTO();

        dto.setId(resena.getId());
        dto.setCalificacion(resena.getCalificacion());
        dto.setComentario(resena.getComentario());
        dto.setFechaCreacion(resena.getFechaCreacion());
        dto.setNombreUsuario(resena.getUsuario().getNombre());
        dto.setNombrePlan(resena.getPlan().getNombre());

        return dto;
    }
}
