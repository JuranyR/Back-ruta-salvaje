package com.proyectoFinal.rutaSalvaje.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReservaRequestDTO {

    @NotNull(message = "La cantidad de personas es obligatoria")
    private Integer cantidadPersonas;

    @NotNull(message = "La fecha de la actividad es obligatoria")
    private LocalDateTime fecha;

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El plan es obligatorio")
    private Long planId;

    public ReservaRequestDTO() {
    }

    public ReservaRequestDTO(Integer cantidadPersonas, LocalDateTime fecha, Long usuarioId, Long planId) {
        this.cantidadPersonas = cantidadPersonas;
        this.fecha = fecha;
        this.usuarioId = usuarioId;
        this.planId = planId;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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
