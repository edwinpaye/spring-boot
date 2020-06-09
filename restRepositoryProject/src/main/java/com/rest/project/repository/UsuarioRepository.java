package com.rest.project.repository;

import com.rest.project.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "usuarios", path = "api/usuarios")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //    @RestResource(path = "findByNombreUsuario", rel = "findByNombreUsuario")
    @RestResource(exported = false)
    Optional<Usuario> findByNombreUsuario( String nombreUsuario);

//    @RestResource(path = "findByEmail", rel = "findByEmail")
    @RestResource(exported = false)
    Optional<Usuario> findByEmail(String email);

}
