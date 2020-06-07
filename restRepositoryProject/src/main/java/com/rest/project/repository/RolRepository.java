package com.rest.project.repository;

import com.rest.project.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "roles", path = "roles")
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
