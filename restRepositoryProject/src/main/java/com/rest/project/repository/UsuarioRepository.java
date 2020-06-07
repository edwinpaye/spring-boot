package com.rest.project.repository;

import com.rest.project.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "productos", path = "productos")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @RestResource(path = "nombreUsuario", rel = "nombreUsuario")
    public Optional<Usuario> findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
}