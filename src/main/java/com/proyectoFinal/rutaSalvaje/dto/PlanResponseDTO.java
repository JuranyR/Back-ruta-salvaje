package com.proyectoFinal.rutaSalvaje.dto;

import com.proyectoFinal.rutaSalvaje.model.Dificultad;
import com.proyectoFinal.rutaSalvaje.model.TipoPlan;

import java.util.List;

public class PlanResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private TipoPlan tipoPlan;
    private Dificultad dificultad;
    private Boolean estado;
    private String actividades;
    private String imagen;
    private List<ResenaResponseDTO> resenas;

    public PlanResponseDTO() {
    }

    public PlanResponseDTO(Long id, String nombre, String descripcion, Double precio, TipoPlan tipoPlan, Dificultad dificultad, Boolean estado, String actividades, String imagen, List<ResenaResponseDTO> resenas) {
        this.id = id;
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

    public List<ResenaResponseDTO> getResenas() {
        return resenas;
    }

    public void setResenas(List<ResenaResponseDTO> resenas) {
        this.resenas = resenas;
    }
}
