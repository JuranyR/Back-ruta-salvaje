package com.proyectoFinal.rutaSalvaje.controller;


import com.proyectoFinal.rutaSalvaje.dto.PlanRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.PlanResponseDTO;
import com.proyectoFinal.rutaSalvaje.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planes")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<PlanResponseDTO> crearPlan(@Valid @RequestBody PlanRequestDTO plan) {
        PlanResponseDTO planCreado = planService.crearPlan(plan);
        return ResponseEntity.status(HttpStatus.CREATED).body(planCreado);
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseDTO>> listarPlanes() {
        List<PlanResponseDTO> planes = planService.listarPlanes();
        return ResponseEntity.ok(planes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDTO> buscarPlan(@PathVariable Long id) {
        PlanResponseDTO plan = planService.buscarPlan(id);
        return ResponseEntity.ok(plan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanResponseDTO> actualizarPlan(@PathVariable Long id, @Valid @RequestBody PlanRequestDTO plan) {
        PlanResponseDTO actualizado = planService.actualizarPlan(id, plan);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlan(@PathVariable Long id) {
        planService.eliminarPlan(id);
        return ResponseEntity.noContent().build();
    }
}