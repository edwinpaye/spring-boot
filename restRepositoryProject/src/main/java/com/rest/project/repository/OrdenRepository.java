package com.rest.project.repository;

import com.rest.project.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "orden", path = "orden")
public interface OrdenRepository extends JpaRepository<Orden, Long> {

}
