package com.rest.project.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no deve estar vacio")
    private String nombre;
    @Column(unique = true)
    private String nombreUsuario;
    @NotBlank(message = "El password no deve estar vacio")
    private String password;
    @NotBlank(message = "El email no deve estar vacio")
    @Email(message = "Email no valido")
    @Column(unique = true)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario(String nombre, String nombreUsuario, String password, String email, Set<Rol> roles) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public Usuario(Long id, String nombre, String nombreUsuario, String password, String email, Set<Rol> roles) {
        this(nombre, nombreUsuario, password, email, roles);
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
