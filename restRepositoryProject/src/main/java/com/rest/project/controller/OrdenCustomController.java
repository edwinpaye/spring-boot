package com.rest.project.controller;

import com.rest.project.entity.Orden;
import com.rest.project.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RepositoryRestController
public class OrdenCustomController {

    @Autowired
    private OrdenRepository repository;

    @Autowired
    private RepositoryEntityLinks repositoryEntityLinks;

    @PostMapping("/orden")
    public ResponseEntity<?> saveOrder(@RequestBody Long[] productosId){
        if (productosId.length<1)
            return ResponseEntity.badRequest().body("orden vacia");
        Orden orden = repository.save(new Orden(
            Stream.of(productosId).map(String::valueOf).collect(Collectors.joining("-"))));
        return ResponseEntity.created(repositoryEntityLinks.linkForItemResource(OrdenRepository.class, orden.getId()).toUri())
            .body(EntityModel.of(orden, Link.of(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString()).withSelfRel()
        ));
//        return ResponseEntity.ok(EntityModel.of(orden,
//            repositoryEntityLinks.linkForItemResource(OrdenRepository.class, orden.getId()).withRel(LinkRelation.of("location")),
//            Link.of(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString()).withSelfRel()
//        ));
    }

    @GetMapping("/orden/{id}")
    public ResponseEntity<EntityModel<Orden>> payOrder(@PathVariable Long id){
        return repository.findById(id).map(orden -> EntityModel.of(orden,
                Link.of(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString()).withSelfRel()
        )).map(ResponseEntity::ok).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/orden/{id}/cancel")
    public ResponseEntity<EntityModel<Orden>> cancelOrder(@PathVariable Long id){
        Orden orden = repository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return null;
    }

    @PostMapping("/orden/{id}/fulfill")
    public ResponseEntity<EntityModel<Orden>> fulfillOrder(@PathVariable Long id){
        
        return null;
    }
}
