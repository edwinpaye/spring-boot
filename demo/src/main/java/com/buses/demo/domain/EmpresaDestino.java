package com.buses.demo.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EmpresaDestino {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Long empresa_id;
    private Long bus_id;
    private Long destino_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_salida;

    public EmpresaDestino() { }

    public EmpresaDestino(Long id, Long empresa_id, Long bus_id, Long destino_id, Date creation, Date fecha_salida) {
        this.id = id;
        this.empresa_id = empresa_id;
        this.bus_id = bus_id;
        this.destino_id = destino_id;
        this.creation = creation;
        this.fecha_salida = fecha_salida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(Long empresa_id) {
        this.empresa_id = empresa_id;
    }

    public Long getBus_id() {
        return bus_id;
    }

    public void setBus_id(Long bus_id) {
        this.bus_id = bus_id;
    }

    public Long getDestino_id() {
        return destino_id;
    }

    public void setDestino_id(Long destino_id) {
        this.destino_id = destino_id;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }
}
