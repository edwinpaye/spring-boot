package com.rest.project.repository;

import com.rest.project.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "usuario", path = "usuario")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @RestResource(path = "nombreEmpiesaCon", rel = "nombreStartsWith")
    Page findByNombreStartsWith(@Param("name") String name, Pageable page);

    //    @RestResource(path = "findByNombreUsuario", rel = "findByNombreUsuario")
    @RestResource(exported = false)
    Optional<Usuario> findByNombreUsuario( String nombreUsuario);

//    @RestResource(path = "findByEmail", rel = "findByEmail")
    @RestResource(exported = false)
    Optional<Usuario> findByEmail(String email);

    @PreAuthorize("hasRole('USER')")
    @Override
    List<Usuario> findAll();

    @PreAuthorize("hasRole('USER')")
    @Override
    Optional<Usuario> findById(Long aLong);

    @RestResource(exported = false)
    @Override
    <S extends Usuario> S save(S s);
}
