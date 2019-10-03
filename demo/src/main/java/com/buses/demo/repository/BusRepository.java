package com.buses.demo.repository;

import com.buses.demo.domain.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
}
