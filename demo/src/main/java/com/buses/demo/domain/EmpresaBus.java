package com.buses.demo.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EmpresaBus {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Long Empresa_id;
    private Long Bus_id;
    private String destino;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizacion;

    public EmpresaBus() { }

    public EmpresaBus(Long id, Long empresa_id, Long bus_id, String destino, Date creation, Date actualizacion) {
        this.id = id;
        Empresa_id = empresa_id;
        Bus_id = bus_id;
        this.destino = destino;
        this.creation = creation;
        this.actualizacion = actualizacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpresa_id() {
        return Empresa_id;
    }

    public void setEmpresa_id(Long empresa_id) {
        Empresa_id = empresa_id;
    }

    public Long getBus_id() {
        return Bus_id;
    }

    public void setBus_id(Long bus_id) {
        Bus_id = bus_id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(Date actualizacion) {
        this.actualizacion = actualizacion;
    }
}
