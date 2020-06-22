package com.rest.project.controller;

import com.rest.project.entity.Producto;
import com.rest.project.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RepositoryRestController
public class ProductoCustomSearchController {

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private PagedResourcesAssembler<Producto> assembler;

    @Autowired
    private RepositoryEntityLinks repositoryEntityLinks;

    @GetMapping(path = "/producto/search/findByCaducidad")
    public ResponseEntity<?> customfindByDate(@Param("date") String date, @PageableDefault Pageable page) throws ParseException {
        Page p = productoRepo.findByCaducidad(new SimpleDateFormat("dd-MM-yyyy").parse(date), page);
        return ResponseEntity.ok(assembler.toModel(p, producto -> EntityModel.of(producto).add(
                //es mejor trabar con RepositoryEntityLinks para obtener uris de otros repositoryControllers
                repositoryEntityLinks.linkForItemResource(ProductoRepository.class, producto.getId()).withSelfRel()
        )).add(repositoryEntityLinks.linkToSearchResource(
                Producto.class, LinkRelation.of("findByCaducidad"), (Pageable) null
        ).withRel("basePath")));
    }

}
