package com.proyectoFinal.rutaSalvaje.controller;


import com.proyectoFinal.rutaSalvaje.dto.ContactoRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.ContactoResponseDTO;
import com.proyectoFinal.rutaSalvaje.dto.UsuarioRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.UsuarioResponseDTO;
import com.proyectoFinal.rutaSalvaje.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crearUsuario (@Valid @RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO usuarioCreado = usuarioService.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar () {
        List<UsuarioResponseDTO> usuariosListados = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuariosListados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId (@PathVariable Long id) {
        UsuarioResponseDTO usuarioObtenido = usuarioService.buscarUsuario(id);
        return ResponseEntity.ok(usuarioObtenido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO actualizado = usuarioService.actualizarUsuario(id, dto);
        return ResponseEntity.ok(actualizado);
    }

}
