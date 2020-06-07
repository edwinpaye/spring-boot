package com.rest.project.repository;

import com.rest.project.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "usuarios")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @RestResource(path = "findByNombreUsuario", rel = "findByNombreUsuario")
    Optional<Usuario> findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);

    @RestResource(path = "findByEmail", rel = "findByEmail")
    Optional<Usuario> findByEmail(@Param("email") String email);

//    @RestResource(exported = false)
//    boolean existByNombreUsuario(String nombreUsuario);

//    @RestResource(exported = false)
//    boolean existByEmail(String email);
}
