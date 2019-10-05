package com.buses.demo.repository;

import com.buses.demo.domain.Destino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinoRepository extends JpaRepository<Destino, Long> {
}
