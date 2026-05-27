package com.proyectoFinal.rutaSalvaje.dto;

import com.proyectoFinal.rutaSalvaje.model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {

    @NotBlank(message = "El campo de nombre es obligatorio")
    private String nombre;

    @Email(message = "Ingresa un correo valido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    @Pattern( regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$", message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial (@#$%^&+=!)" )
    private String password;

    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    @Pattern( regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$", message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial (@#$%^&+=!)" )
    @NotBlank(message = "Confirmar la contrasena")
    private String confirmarPassword;

    @NotBlank(message = "El campo de telefono de emergencia es obligatorio")
    @Size(min = 7, max = 10, message = ("El telefono debe tener entre 7 y 10 digitos"))
    private String telefono;

    @NotBlank(message = "El campo de nombre de emergencia es obligatorio")
    private String nombreEmergencia;

    @NotBlank(message = "El campo de telefono de emergencia es obligatorio")
    @Size(min = 7, max = 10, message = "El telefono de emergencia debe tener entre 7 y 10 dígitos")
    private String telefonoEmergencia;

    @NotBlank(message = "El campo de parentesco es obligatorio")
    private String parentesco;

    private Rol rol;

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(String nombre, String email, String password, String confirmarPassword, String telefono, String nombreEmergencia, String telefonoEmergencia, String parentesco) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.confirmarPassword = confirmarPassword;
        this.telefono = telefono;
        this.nombreEmergencia = nombreEmergencia;
        this.telefonoEmergencia = telefonoEmergencia;
        this.parentesco = parentesco;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }
}
