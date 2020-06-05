package com.rest.project.repository;

import com.rest.project.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RepositoryRestResource(collectionResourceRel = "productos", path = "productos")
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    SimpleDateFormat sDF = new SimpleDateFormat("dd-MM-yyyy");

    @RestResource(path = "nombreEmpiesaCon", rel = "nombreStartsWith")
    public Page findByNombreStartsWith(@Param("name") String name, Pageable page);

    @RestResource(exported = false)
    public Page findByCaducidad(Date date, Pageable page);

//    @RestResource
//    @Query("select p from Producto p where p.caducidad like %?1")
//    public Page caducidadByString(@Param("date") String date, Pageable page);
//
//    @RestResource(path = "caducidad", rel = "caducidadRel", exported = true)
//    public default Page caducidad(@Param("date") String date, Pageable page) throws ParseException {
//        return findByCaducidad(sDF.parse(date), page);
//    }
}
