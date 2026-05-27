package com.proyectoFinal.rutaSalvaje.dto;

import com.proyectoFinal.rutaSalvaje.model.EstadoReserva;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReservaResponseDTO {

    private Long id;
    private Integer cantidadPersonas;
    private Double total;
    private LocalDateTime fecha;
    private LocalDateTime fechaCreacion;
    private EstadoReserva estado;
    private String nombreUsuario;
    private String nombrePlan;

    public ReservaResponseDTO() {
    }

    public ReservaResponseDTO(Long id, Integer cantidadPersonas, Double total, LocalDateTime fecha, LocalDateTime fechaCreacion, EstadoReserva estado, String nombreUsuario, String nombrePlan) {
        this.id = id;
        this.cantidadPersonas = cantidadPersonas;
        this.total = total;
        this.fecha = fecha;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.nombreUsuario = nombreUsuario;
        this.nombrePlan = nombrePlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
