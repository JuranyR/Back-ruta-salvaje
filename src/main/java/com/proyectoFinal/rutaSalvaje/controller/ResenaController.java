package com.proyectoFinal.rutaSalvaje.controller;


import com.proyectoFinal.rutaSalvaje.dto.ResenaRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.ResenaResponseDTO;
import com.proyectoFinal.rutaSalvaje.service.ResenaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
@SecurityRequirement(name = "bearerAuth")
public class ResenaController {

    private final ResenaService resenaService;

    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    @PostMapping
    public ResponseEntity<ResenaResponseDTO> crearResena(@Valid @RequestBody ResenaRequestDTO dto) {
        ResenaResponseDTO resenaCreada = resenaService.crarResena(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resenaCreada);
    }

    @GetMapping
    public ResponseEntity<List<ResenaResponseDTO>> listarResenas() {
        List<ResenaResponseDTO> resenas = resenaService.listarResenas();
        return ResponseEntity.ok(resenas);
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<List<ResenaResponseDTO>> listarResenasPorPlan(@PathVariable Long planId) {
        List<ResenaResponseDTO> resenas = resenaService.listarResenasPorPlan(planId);
        return ResponseEntity.ok(resenas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaResponseDTO> buscarResena(@PathVariable Long id) {
        ResenaResponseDTO resena = resenaService.buscarResena(id);
        return ResponseEntity.ok(resena);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Long id) {
        resenaService.eliminarResena(id);
        return ResponseEntity.noContent().build();
    }
}
