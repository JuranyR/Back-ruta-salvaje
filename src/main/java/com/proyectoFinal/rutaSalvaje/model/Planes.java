package com.proyectoFinal.rutaSalvaje.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="planes")
public class Planes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre tiene que estar")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(nullable = false)
    private Double precio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPlan plan;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Dificultad dificultad;


    @Column(nullable = false)
    private Boolean estado;

    @NotBlank(message = "Las actividades son obligatorias")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String actividades;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @NotBlank(message = "La imagen es obligatoria")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String imagen;

    public Planes() {
    }

    public Planes(String nombre, Double precio, TipoPlan plan, Dificultad dificultad, Boolean estado, String actividades, String descripcion, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.plan = plan;
        this.dificultad = dificultad;
        this.estado = estado;
        this.actividades = actividades;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank(message = "El nombre tiene que estar") String getNombre() {
        return nombre;
    }

    public @NotNull(message = "El precio es obligatorio") @Min(value = 0, message = "El precio no puede ser negativo") Double getPrecio() {
        return precio;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public TipoPlan getPlan() {
        return plan;
    }

    public Boolean getEstado() {
        return estado;
    }

    public @NotBlank(message = "Las actividades son obligatorias") String getActividades() {
        return actividades;
    }

    public @NotBlank(message = "La descripción es obligatoria") String getDescripcion() {
        return descripcion;
    }

    public @NotBlank(message = "La imagen es obligatoria") String getImagen() {
        return imagen;
    }

    public void setNombre(@NotBlank(message = "El nombre tiene que estar") String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(@NotNull(message = "El precio es obligatorio") @Min(value = 0, message = "El precio no puede ser negativo") Double precio) {
        this.precio = precio;
    }

    public void setPlan(TipoPlan plan) {
        this.plan = plan;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setActividades(@NotBlank(message = "Las actividades son obligatorias") String actividades) {
        this.actividades = actividades;
    }

    public void setDescripcion(@NotBlank(message = "La descripción es obligatoria") String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(@NotBlank(message = "La imagen es obligatoria") String imagen) {
        this.imagen = imagen;
    }
}
