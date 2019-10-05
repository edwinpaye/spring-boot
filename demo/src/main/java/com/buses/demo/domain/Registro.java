package com.buses.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

//@Entity
public class Registro {

//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Long bus_id;
    private Long empresa_bus_id;
    private Long empresa_usuario_id;
    private Date creacion;
}
