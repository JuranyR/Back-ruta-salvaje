package com.proyectoFinal.rutaSalvaje.mapper;

import com.proyectoFinal.rutaSalvaje.dto.PlanRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.PlanResponseDTO;
import com.proyectoFinal.rutaSalvaje.dto.ResenaResponseDTO;
import com.proyectoFinal.rutaSalvaje.model.Plan;
import com.proyectoFinal.rutaSalvaje.model.Resena;

import java.util.ArrayList;
import java.util.List;

public class PlanMapper {

    public static Plan aEntidad(PlanRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Plan plan = new Plan();

        plan.setNombre(dto.getNombre());
        plan.setDescripcion(dto.getDescripcion());
        plan.setPrecio(dto.getPrecio());
        plan.setDescuentoPorcentaje(dto.getDescuentoPorcentaje());
        plan.setTipoPlan(dto.getTipoPlan());
        plan.setDificultad(dto.getDificultad());
        plan.setEstado(dto.getEstado());
        plan.setActividades(dto.getActividades());
        plan.setImagen(dto.getImagen());

        return plan;
    }

    public static PlanResponseDTO aDTO(Plan plan) {

        if (plan == null) {
            return null;
        }

        PlanResponseDTO dto = new PlanResponseDTO();

        dto.setId(plan.getId());
        dto.setNombre(plan.getNombre());
        dto.setDescripcion(plan.getDescripcion());
        dto.setPrecio(plan.getPrecio());
        dto.setDescuentoPorcentaje(plan.getDescuentoPorcentaje());
        dto.setPrecioFinal(precioFinal(plan));
        dto.setTipoPlan(plan.getTipoPlan());
        dto.setDificultad(plan.getDificultad());
        dto.setEstado(plan.getEstado());
        dto.setActividades(plan.getActividades());
        dto.setImagen(plan.getImagen());

        List<ResenaResponseDTO> resenas = new ArrayList<>();

        for (Resena r : plan.getResenas()) {
            ResenaResponseDTO resenaDTO = ResenaMapper.aDTO(r);
            resenas.add(resenaDTO);
        }

        dto.setResenas(resenas);

        return dto;
    }

    private static Double precioFinal(Plan plan) {
        Double descuento = plan.getDescuentoPorcentaje() == null ? 0.0 : plan.getDescuentoPorcentaje();
        return plan.getPrecio() - (plan.getPrecio() * descuento / 100.0);
    }
}
