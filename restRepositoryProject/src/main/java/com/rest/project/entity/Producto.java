package com.rest.project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "nombre must not be empty")
    @Column(unique = true)
    private String nombre;
    private Float precio;
    @NotBlank(message = "caducidad no debe estar vacio")
    @Temporal(TemporalType.DATE)
    private Date caducidad;

    public Producto() {}

    public Producto(Long id, String nombre, Float precio, Date caducidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.caducidad = caducidad;
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

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }
}
