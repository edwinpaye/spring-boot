package com.rest.project.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {

    @NotBlank(message = "nombre usuario must not be empty")
    private String nombreUsuario;

    @NotBlank(message = "password must not be empty")
    private String password;


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
