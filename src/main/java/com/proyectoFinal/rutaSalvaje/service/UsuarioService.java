package com.proyectoFinal.rutaSalvaje.service;

import com.proyectoFinal.rutaSalvaje.dto.UsuarioRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.UsuarioResponseDTO;
import com.proyectoFinal.rutaSalvaje.mapper.UsuarioMapper;
import com.proyectoFinal.rutaSalvaje.model.Contacto;
import com.proyectoFinal.rutaSalvaje.model.Usuario;
import com.proyectoFinal.rutaSalvaje.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO crearUsuario (UsuarioRequestDTO usuarioDTO) {
        Optional<Usuario> existeCorreo = usuarioRepository.findByEmail(usuarioDTO.getEmail().trim().toLowerCase());

        if (existeCorreo.isPresent()) {
            throw new IllegalArgumentException("Ya existe un mensaje con este email");
        }

        if (!usuarioDTO.getPassword().equals(usuarioDTO.getConfirmarPassword())) {
            throw new IllegalArgumentException("Las contrasenas no coinciden");
        }

        if (!usuarioDTO.getTelefono().matches("\\d{7,10}")) {
            throw new IllegalArgumentException("El teléfono debe contener solo números entre 7 y 10 dígitos");
        }

        Usuario usuario = UsuarioMapper.aEntidad(usuarioDTO);

        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        Usuario guardarDB = usuarioRepository.save(usuario);

        return UsuarioMapper.aDTO(guardarDB);
    }

    public List<UsuarioResponseDTO> listarUsuarios () {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> usuarioDto = new ArrayList<>();

        for (Usuario u : usuarios) {
            UsuarioResponseDTO dto = UsuarioMapper.aDTO(u);
            usuarioDto.add(dto);
        }

        return usuarioDto;
    }

    public UsuarioResponseDTO buscarUsuario (Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser menor a 0");
        }

        Usuario existeUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario con ID " + id + " no existe"));


        return UsuarioMapper.aDTO(existeUsuario);
    }

    public void eliminarUsuario (Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser menor a 0");
        }

        Usuario existeUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario con ID " + id + " no existe"));

        usuarioRepository.deleteById(existeUsuario.getId());
    }

    public UsuarioResponseDTO actualizarUsuario (Long id, UsuarioRequestDTO nuevoUsuario) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser menor a 0");
        }

        if (nuevoUsuario.getEmail() == null || nuevoUsuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El correo del usuario no puede estar vacio");
        }

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario con ID " + id + " no existe"));

        if (!usuario.getEmail().equals(nuevoUsuario.getEmail().trim().toLowerCase())) {

            Optional<Usuario> existeCorreo = usuarioRepository.findByEmail(
                    nuevoUsuario.getEmail().trim().toLowerCase()
            );

            if (existeCorreo.isPresent()) {
                throw new IllegalArgumentException("El correo ya está registrado");
            }
        }

        if (!nuevoUsuario.getTelefono().matches("\\d{7,10}")) {
            throw new IllegalArgumentException("El teléfono debe contener solo números entre 7 y 10 dígitos");
        }

        usuario.setNombre(nuevoUsuario.getNombre());
        usuario.setEmail(nuevoUsuario.getEmail().trim().toLowerCase());
        usuario.setTelefono(nuevoUsuario.getTelefono());
        usuario.setNombreEmergencia(nuevoUsuario.getNombreEmergencia());
        usuario.setTelefonoEmergencia(nuevoUsuario.getTelefonoEmergencia());
        usuario.setParentesco(nuevoUsuario.getParentesco());

        Usuario actualizado = usuarioRepository.save(usuario);

        return UsuarioMapper.aDTO(actualizado);
    }
}
