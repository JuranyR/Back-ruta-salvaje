package com.proyectoFinal.rutaSalvaje.service;


import com.proyectoFinal.rutaSalvaje.dto.PlanRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.PlanResponseDTO;
import com.proyectoFinal.rutaSalvaje.mapper.PlanMapper;
import com.proyectoFinal.rutaSalvaje.model.Plan;
import com.proyectoFinal.rutaSalvaje.repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public PlanResponseDTO crearPlan (PlanRequestDTO planDTO) {
        if (planDTO.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        Plan plan = PlanMapper.aEntidad(planDTO);

        Plan guardarDB = planRepository.save(plan);

        return PlanMapper.aDTO(guardarDB);
    }

    @Transactional
    public List<PlanResponseDTO> listarPlanes () {
        List<Plan> planes = planRepository.findAll();
        List<PlanResponseDTO> planesDTO = new ArrayList<>();

        for (Plan p : planes) {
            PlanResponseDTO dto = PlanMapper.aDTO(p);
            planesDTO.add(dto);
        }

        return planesDTO;
    }

    @Transactional
    public PlanResponseDTO buscarPlan (Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser menor a 0");
        }

        Plan existePlan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El plan con ID " + id + " no existe"));


        return PlanMapper.aDTO(existePlan);
    }

    public void eliminarPlan (Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser menor a 0");
        }

        Plan existePlan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El plan con ID " + id + " no existe"));


        planRepository.deleteById(existePlan.getId());

    }

    public PlanResponseDTO actualizarPlan (Long id, PlanResponseDTO nuevoPlan) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser menor a 0");
        }

        Plan existePlan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El plan con ID " + id + " no existe"));

        if (nuevoPlan.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        existePlan.setNombre(nuevoPlan.getNombre());
        existePlan.setDescripcion(nuevoPlan.getDescripcion());
        existePlan.setPrecio(nuevoPlan.getPrecio());
        existePlan.setDificultad(nuevoPlan.getDificultad());
        existePlan.setTipoPlan(nuevoPlan.getTipoPlan());
        existePlan.setEstado(nuevoPlan.getEstado());
        existePlan.setActividades(nuevoPlan.getActividades());
        existePlan.setImagen(nuevoPlan.getImagen());

        Plan planActualizado = planRepository.save(existePlan);

        return PlanMapper.aDTO(planActualizado);
    }

}
