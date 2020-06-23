package com.rest.project.controller;

import com.rest.project.entity.Orden;
import com.rest.project.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rest.project.enums.OrderStatus;

@RepositoryRestController
@RequestMapping("/api/orden")
public class OrdenCustomController {

    @Autowired
    private OrdenRepository repository;

    @Autowired
    private RepositoryEntityLinks repositoryEntityLinks;

    @PostMapping("/nuevo")
    public ResponseEntity<?> saveOrder(@RequestBody Long[] productosId){
        if (productosId.length<1)
            return ResponseEntity.badRequest().body("orden vacia");
        Orden orden = repository.save(new Orden(
            Stream.of(productosId).map(String::valueOf).collect(Collectors.joining("-"))));
        return ResponseEntity.created(repositoryEntityLinks.linkForItemResource(
                OrdenRepository.class, orden.getId()).toUri()).body(EntityModel.of(orden));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<?> payOrder(@PathVariable Long id){
        return transitionTo(id, OrderStatus.PAID_FOR);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id){
        return transitionTo(id, OrderStatus.CANCELED);
    }

    @PostMapping("/{id}/fulfill")
    public ResponseEntity<?> fulfillOrder(@PathVariable Long id){
        return transitionTo(id, OrderStatus.FULFILLED);
    }

    private ResponseEntity<?> transitionTo(Long id, OrderStatus status) throws ResourceNotFoundException{
        Orden order = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
        if (OrderStatus.valid(order.getOrderStatus(), status)){
            order.setOrderStatus(status);
            return ResponseEntity.ok(EntityModel.of(repository.save(order)));
        }
        return ResponseEntity.badRequest().body(
                "Transitioning from "+order.getOrderStatus()+" to "+status+" is not valid");
    }
}
