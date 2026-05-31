package com.proyectoFinal.rutaSalvaje.service;

import com.proyectoFinal.rutaSalvaje.dto.ResenaRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.ResenaResponseDTO;
import com.proyectoFinal.rutaSalvaje.mapper.ResenaMapper;
import com.proyectoFinal.rutaSalvaje.model.Plan;
import com.proyectoFinal.rutaSalvaje.model.Resena;
import com.proyectoFinal.rutaSalvaje.model.Usuario;
import com.proyectoFinal.rutaSalvaje.repository.PlanRepository;
import com.proyectoFinal.rutaSalvaje.repository.ResenaRepository;
import com.proyectoFinal.rutaSalvaje.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResenaService {

    private final ResenaRepository resenaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanRepository planRepository;

    public ResenaService(ResenaRepository resenaRepository, UsuarioRepository usuarioRepository, PlanRepository planRepository) {
        this.resenaRepository = resenaRepository;
        this.usuarioRepository = usuarioRepository;
        this.planRepository = planRepository;
    }

    public ResenaResponseDTO crarResena (ResenaRequestDTO resenaDTO) {

        Usuario usuario = usuarioRepository.findById(resenaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        Plan plan = planRepository.findById(resenaDTO.getPlanId())
                .orElseThrow(() -> new RuntimeException("El plan no existe"));

        if (!plan.getEstado()) {
            throw new IllegalArgumentException("No se puede reseñar un plan inactivo");
        }

        Resena resena = ResenaMapper.aEntidad(resenaDTO, usuario, plan);
        Resena guardada = resenaRepository.save(resena);
        return ResenaMapper.aDTO(guardada);
    }

    public List<ResenaResponseDTO> listarResenas () {
        List<Resena> resenas = resenaRepository.findAll();
        return convertirLista(resenas);
    }

    public List<ResenaResponseDTO> listarResenasPorPlan(Long planId) {
        if (planId == null || planId <= 0) {
            throw new IllegalArgumentException("El ID del plan no puede ser null");
        }

        if (!planRepository.existsById(planId)) {
            throw new RuntimeException("El plan no existe");
        }

        return convertirLista(resenaRepository.findByPlanIdOrderByFechaCreacionDesc(planId));
    }

    private List<ResenaResponseDTO> convertirLista(List<Resena> resenas) {
        List<ResenaResponseDTO> resenasDTO = new ArrayList<>();

        for (Resena r : resenas) {
            ResenaResponseDTO dto = ResenaMapper.aDTO(r);
            resenasDTO.add(dto);
        }

        return resenasDTO;
    }

    public ResenaResponseDTO buscarResena (Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }

        Resena existeResena = resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La resena con ID " + id + " no existe"));

        return ResenaMapper.aDTO(existeResena);
    }

    public void eliminarResena (Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }

        Resena existeResena = resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La resena con ID " + id + " no existe"));

        resenaRepository.deleteById(existeResena.getId());
    }

}
