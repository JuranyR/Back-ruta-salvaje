package com.proyectoFinal.rutaSalvaje.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ResenaRequestDTO {

    @NotBlank(message = "El comentario es obligatorio")
    private String comentario;

    @NotNull(message = "La calificacion es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Integer calificacion;

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El pla es obligatorio")
    private Long planId;

    public ResenaRequestDTO(String comentario, Integer calificacion, Long usuarioId, Long planId) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.usuarioId = usuarioId;
        this.planId = planId;
    }

    public ResenaRequestDTO() {
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
}
