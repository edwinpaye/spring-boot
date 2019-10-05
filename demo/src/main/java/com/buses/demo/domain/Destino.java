package com.buses.demo.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creation;
    private String nombre;
    private String ubicacion;

    public Destino() { }

    public Destino(Long id, Date creation, String nombre, String ubicacion) {
        this.id = id;
        this.creation = creation;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
