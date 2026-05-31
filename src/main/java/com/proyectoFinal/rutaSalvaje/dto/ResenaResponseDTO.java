package com.proyectoFinal.rutaSalvaje.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ResenaResponseDTO {

    private Long id;
    private String comentario;
    private Integer calificacion;
    private Long usuarioId;
    private Long planId;
    private String nombreUsuario;
    private String nombrePlan;
    private LocalDateTime fechaCreacion;

    public ResenaResponseDTO(Long id, String comentario, Integer calificacion, Long usuarioId, Long planId, String nombreUsuario, String nombrePlan, LocalDateTime fechaCreacion) {
        this.id = id;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.usuarioId = usuarioId;
        this.planId = planId;
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
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
