package com.rest.project.repository;

import com.rest.project.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
