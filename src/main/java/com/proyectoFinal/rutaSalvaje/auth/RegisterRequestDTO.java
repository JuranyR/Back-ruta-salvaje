package com.proyectoFinal.rutaSalvaje.auth;

public class RegisterRequestDTO {
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String nombreEmergencia;
    private String telefonoEmergencia;
    private String parentesco;

    public RegisterRequestDTO() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getNombreEmergencia() { return nombreEmergencia; }
    public void setNombreEmergencia(String nombreEmergencia) { this.nombreEmergencia = nombreEmergencia; }
    public String getTelefonoEmergencia() { return telefonoEmergencia; }
    public void setTelefonoEmergencia(String telefonoEmergencia) { this.telefonoEmergencia = telefonoEmergencia; }
    public String getParentesco() { return parentesco; }
    public void setParentesco(String parentesco) { this.parentesco = parentesco; }
}