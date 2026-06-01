package com.proyectoFinal.rutaSalvaje.controller;


import com.proyectoFinal.rutaSalvaje.dto.ContactoRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.ContactoResponseDTO;
import com.proyectoFinal.rutaSalvaje.service.ContactoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactos")
@SecurityRequirement(name = "bearerAuth")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @PostMapping
    public ResponseEntity<ContactoResponseDTO> crearMensaje (@Valid @RequestBody ContactoRequestDTO mensaje) {
        ContactoResponseDTO mensajeCreado = contactoService.crearMensaje(mensaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensajeCreado);
    }

    @GetMapping
    public ResponseEntity<List<ContactoResponseDTO>> listar () {
        List<ContactoResponseDTO> mensajesListados = contactoService.listarMensajes();
        return ResponseEntity.ok(mensajesListados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMensaje (@PathVariable Long id) {
        contactoService.eliminarMensaje(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoResponseDTO> obtenerPorId (@PathVariable Long id) {
        ContactoResponseDTO mensajeoObtenido = contactoService.buscarMensaje(id);
        return ResponseEntity.ok(mensajeoObtenido);
    }
}
