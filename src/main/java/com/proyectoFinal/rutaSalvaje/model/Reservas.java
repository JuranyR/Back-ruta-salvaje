package com.proyectoFinal.rutaSalvaje.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="reservas")
public class Reservas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Integer cantidad_personas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReserva estado;

    public Reservas() {}

    public Reservas(LocalDateTime fecha, Double precio, Integer cantidad_personas, Double total, EstadoReserva estado) {
        this.fecha = fecha;
        this.precio = precio;
        this.cantidad_personas = cantidad_personas;
        this.total = total;
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Double getPrecio() {
        return precio;
    }

    public Double getTotal() {
        return total;
    }

    public Integer getCantidad_personas() {
        return cantidad_personas;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public Long getId() {
        return id;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public void setCantidad_personas(Integer cantidad_personas) {
        this.cantidad_personas = cantidad_personas;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
