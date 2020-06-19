package com.rest.project.repository;

import com.rest.project.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;

@RepositoryRestResource(collectionResourceRel = "producto", path = "producto")
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @RestResource(path = "nombreEmpiesaCon", rel = "nombreStartsWith")
    Page findByNombreStartsWith(String name, Pageable page);

    Page findByCaducidad(Date date, Pageable page);

}
