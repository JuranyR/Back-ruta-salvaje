package com.proyectoFinal.rutaSalvaje.controller;


import com.proyectoFinal.rutaSalvaje.dto.PlanRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.PlanResponseDTO;
import com.proyectoFinal.rutaSalvaje.service.CloudinaryService;
import com.proyectoFinal.rutaSalvaje.service.PlanService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.proyectoFinal.rutaSalvaje.model.Dificultad;
import com.proyectoFinal.rutaSalvaje.model.TipoPlan;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/planes")
@SecurityRequirement(name = "bearerAuth")
public class PlanController {

    private final PlanService planService;
    private final CloudinaryService cloudinaryService; // 2. Inyecta el servicio

    public PlanController(PlanService planService, CloudinaryService cloudinaryService) {
        this.planService = planService;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<PlanResponseDTO> crearPlan(
            @RequestParam("file") MultipartFile file,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio,
            @RequestParam(value = "descuentoPorcentaje", defaultValue = "0") Double descuentoPorcentaje,
            @RequestParam("tipoPlan") TipoPlan tipoPlan,
            @RequestParam("dificultad") Dificultad dificultad,
            @RequestParam("estado") Boolean estado,
            @RequestParam("actividades") String actividades) throws IOException {

        String urlImagen = cloudinaryService.uploadFile(file);

        PlanRequestDTO planDTO = new PlanRequestDTO();
        planDTO.setNombre(nombre);
        planDTO.setDescripcion(descripcion);
        planDTO.setPrecio(precio);
        planDTO.setDescuentoPorcentaje(descuentoPorcentaje);
        planDTO.setTipoPlan(tipoPlan);
        planDTO.setDificultad(dificultad);
        planDTO.setEstado(estado);
        planDTO.setActividades(actividades);
        planDTO.setImagen(urlImagen);

        PlanResponseDTO planCreado = planService.crearPlan(planDTO);
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
