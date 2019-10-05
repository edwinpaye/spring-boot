package com.buses.demo.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String matricula;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    private String destino;

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Bus() { }

    public Bus(Long id, String matricula, Date creation, String destino) {
        this.id = id;
        this.matricula = matricula;
        this.creation = creation;
        this.destino = destino;
    }
}
