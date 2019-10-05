package com.buses.demo.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    private String nombre;
    private int telefono;
    private String caza_matriz;
//    @OneToMany
//    private List<Bus> bus;
//    @OneToOne
//    private Usuario admin;
//    @ManyToOne
//    private Usuario personal;

    public Empresa() {
    }

    public Empresa(Long id, Date creation, String nombre, int telefono, String caza_matriz) {
        this.id = id;
        this.creation = creation;
        this.nombre = nombre;
        this.telefono = telefono;
        this.caza_matriz = caza_matriz;
    }

    public String getCaza_matriz() {
        return caza_matriz;
    }

    public void setCaza_matriz(String caza_matriz) {
        this.caza_matriz = caza_matriz;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

}
