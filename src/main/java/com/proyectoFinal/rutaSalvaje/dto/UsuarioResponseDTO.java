package com.proyectoFinal.rutaSalvaje.dto;

import com.proyectoFinal.rutaSalvaje.model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String nombreEmergencia;
    private String telefonoEmergencia;
    private String parentesco;
    private Rol rol;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Long id, String nombre, String email, String telefono, String nombreEmergencia, String telefonoEmergencia, String parentesco, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.nombreEmergencia = nombreEmergencia;
        this.telefonoEmergencia = telefonoEmergencia;
        this.parentesco = parentesco;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreEmergencia() {
        return nombreEmergencia;
    }

    public void setNombreEmergencia(String nombreEmergencia) {
        this.nombreEmergencia = nombreEmergencia;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
