package com.buses.demo.repository;

import com.buses.demo.domain.EmpresaBus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaBusRepository extends JpaRepository<EmpresaBus, Long> {
}
