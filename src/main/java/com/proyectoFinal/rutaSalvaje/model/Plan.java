package com.proyectoFinal.rutaSalvaje.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="planes")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(nullable = false)
    private Double precio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPlan tipoPlan;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Dificultad dificultad;

    @Column(nullable = false)
    private Boolean estado;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String actividades;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String imagen;

    @OneToMany(mappedBy = "plan", fetch = FetchType.LAZY)
    private List<Resena> resenas = new ArrayList<>();

    public Plan() {
    }

    public Plan(String nombre, String descripcion, Double precio, TipoPlan tipoPlan, Dificultad dificultad, Boolean estado, String actividades, String imagen, List<Resena> resenas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipoPlan = tipoPlan;
        this.dificultad = dificultad;
        this.estado = estado;
        this.actividades = actividades;
        this.imagen = imagen;
        this.resenas = resenas;
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

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }
}
