package com.buses.demo.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EmpresaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Long empresa_id;
    private Long usuaio_id;
    private String permiso_usuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creacion;

    public EmpresaUsuario() { }

    public EmpresaUsuario(Long id, Long empresa_id, Long usuaio_id, String permiso_usuario, Date creacion) {
        this.id = id;
        this.empresa_id = empresa_id;
        this.usuaio_id = usuaio_id;
        this.permiso_usuario = permiso_usuario;
        this.creacion = creacion;
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

    public Long getUsuaio_id() {
        return usuaio_id;
    }

    public void setUsuaio_id(Long usuaio_id) {
        this.usuaio_id = usuaio_id;
    }

    public String getPermiso_usuario() {
        return permiso_usuario;
    }

    public void setPermiso_usuario(String permiso_usuario) {
        this.permiso_usuario = permiso_usuario;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }
}
