package com.proyectoFinal.rutaSalvaje.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ResenaResponseDTO {

    private Long id;
    private String comentario;
    private Integer calificacion;
    private String nombreUsuario;
    private String nombrePlan;
    private LocalDateTime fechaCreacion;

    public ResenaResponseDTO(Long id, String comentario, Integer calificacion, String nombreUsuario, String nombrePlan, LocalDateTime fechaCreacion) {
        this.id = id;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.nombreUsuario = nombreUsuario;
        this.nombrePlan = nombrePlan;
        this.fechaCreacion = fechaCreacion;
    }

    public ResenaResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
