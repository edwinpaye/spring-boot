package com.rest.project.repository;

import com.rest.project.entity.Rol;
import com.rest.project.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

//@PreAuthorize("hasRole('ADMIN')")
@RepositoryRestResource(collectionResourceRel = "rol", path = "rol")
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
