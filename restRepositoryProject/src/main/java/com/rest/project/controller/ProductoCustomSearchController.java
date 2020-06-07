package com.rest.project.controller;

import com.rest.project.entity.Producto;
import com.rest.project.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@BasePathAwareController
@RequestMapping("productos/search")
public class ProductoCustomSearchController implements RepresentationModelProcessor<RepositorySearchesResource> {

    @Autowired
    ProductoRepository productoRepo;

    @Autowired
    PagedResourcesAssembler<Producto> assembler;

    @Autowired
    RepositoryEntityLinks repositoryEntityLinks;

    @GetMapping(path = "find")
    public ResponseEntity<?> customfindByDate(@Param("date") String date, @PageableDefault Pageable page) throws ParseException {
        Page p = productoRepo.findByCaducidad(new SimpleDateFormat("dd-MM-yyyy").parse(date), page);
        return ResponseEntity.ok(assembler.toModel(p, producto -> {
            Link productoLink = repositoryEntityLinks.linkToItemResource(Producto.class, producto.getId());
            return EntityModel.of(producto).add(
                productoLink.withSelfRel(), productoLink
        );}));
    }

    @Override
    public RepositorySearchesResource process(RepositorySearchesResource model) {
        final Link link = linkTo(ProductoCustomSearchController.class)
                .slash("find?date=dd-mm-yyyy&page=0&size=1&sort=caducidad,asc").withRel("findByDate");
        model.add(link);
        return model;
    }
}
