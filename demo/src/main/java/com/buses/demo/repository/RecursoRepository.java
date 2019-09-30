package com.buses.demo.repository;

import com.buses.demo.domain.Recurso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecursoRepository extends CrudRepository<Recurso, Long> {
//    @Query("select u from Recurso u where u.name like %?1")
//    List<Recurso> findByName(String name);
}
