package com.rest.project.repository;

import com.rest.project.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
