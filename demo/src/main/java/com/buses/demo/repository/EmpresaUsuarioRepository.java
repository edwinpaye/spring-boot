package com.buses.demo.repository;

import com.buses.demo.domain.EmpresaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaUsuarioRepository extends JpaRepository<EmpresaUsuario, Long> {
}
