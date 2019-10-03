package com.buses.demo.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    private String nombre;
    @ManyToOne
    private Bus bus;
    private int telefono;
    @OneToOne
    private Usuario admin;
    @ManyToOne
    private Usuario personal;

    public Empresa(Long id, Date creation, String nombre, Bus bus, int telefono, Usuario admin, Usuario personal) {
        this.id = id;
        this.creation = creation;
        this.nombre = nombre;
        this.bus = bus;
        this.telefono = telefono;
        this.admin = admin;
        this.personal = personal;
    }

    public Empresa() {
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

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Usuario getAdmin() {
        return admin;
    }

    public void setAdmin(Usuario admin) {
        this.admin = admin;
    }

    public Usuario getPersonal() {
        return personal;
    }

    public void setPersonal(Usuario personal) {
        this.personal = personal;
    }
}
