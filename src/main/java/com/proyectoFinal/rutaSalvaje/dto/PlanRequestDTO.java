package com.proyectoFinal.rutaSalvaje.dto;

import com.proyectoFinal.rutaSalvaje.model.Dificultad;
import com.proyectoFinal.rutaSalvaje.model.TipoPlan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlanRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    @NotNull(message = "El tipo de plan es obligatoria")
    private TipoPlan tipoPlan;

    @NotNull(message = "La dificultad es obligatoria")
    private Dificultad dificultad;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @NotBlank(message = "Las actividades son obligatorias")
    private String actividades;

    @NotBlank(message = "La imagen es obligatoria")
    private String imagen;

    public PlanRequestDTO() {
    }

    public PlanRequestDTO(String nombre, String descripcion, Double precio, TipoPlan tipoPlan, Dificultad dificultad, Boolean estado, String actividades, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipoPlan = tipoPlan;
        this.dificultad = dificultad;
        this.estado = estado;
        this.actividades = actividades;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public TipoPlan getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(TipoPlan tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
