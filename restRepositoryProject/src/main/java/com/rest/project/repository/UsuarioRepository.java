package com.rest.project.repository;

import com.rest.project.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "productos", path = "productos")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//    @RestResource(path = "findByNombreUsuario", rel = "findByNombreUsuario")
//    public Optional<Usuario> findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);

    @RestResource(path = "existByNombreUsuario", rel = "existByNombreUsuario")
    public boolean existByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);

    @RestResource(path = "existByEmail", rel = "existByEmail")
    public boolean existByEmail(@Param("email") String email);
}
