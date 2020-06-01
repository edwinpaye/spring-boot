package com.rest.project.repository;

import com.rest.project.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
