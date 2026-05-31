package com.proyectoFinal.rutaSalvaje.mapper;

import com.proyectoFinal.rutaSalvaje.dto.UsuarioRequestDTO;
import com.proyectoFinal.rutaSalvaje.dto.UsuarioResponseDTO;
import com.proyectoFinal.rutaSalvaje.model.Rol;
import com.proyectoFinal.rutaSalvaje.model.Usuario;

public class UsuarioMapper {

    public static Usuario aEntidad (UsuarioRequestDTO usuarioDto) {
        if (usuarioDto == null) return null;

        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioDto.getEmail());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setParentesco(usuarioDto.getParentesco());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setNombreEmergencia(usuarioDto.getNombreEmergencia());
        usuario.setTelefonoEmergencia(usuarioDto.getTelefonoEmergencia());
        usuario.setRol(Rol.USUARIO);


        return usuario;
    }

    public static UsuarioResponseDTO aDTO (Usuario usuario) {
        if (usuario == null) return null;

        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setNombreEmergencia(usuario.getNombreEmergencia());
        dto.setTelefonoEmergencia(usuario.getTelefonoEmergencia());
        dto.setParentesco(usuario.getParentesco());

        dto.setRol(usuario.getRol());

        return dto;

    }
}
