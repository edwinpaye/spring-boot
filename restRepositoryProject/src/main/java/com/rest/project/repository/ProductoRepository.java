package com.rest.project.repository;

import com.rest.project.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;

@RepositoryRestResource(collectionResourceRel = "productos", path = "productos")
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @RestResource(path = "nombreEmpiesaCon", rel = "nombreStartsWith")
    public Page findByNombreStartsWith(@Param("name") String name, Pageable page);

    @RestResource(exported = false)
    public Page findByCaducidad(Date date, Pageable page);

}
