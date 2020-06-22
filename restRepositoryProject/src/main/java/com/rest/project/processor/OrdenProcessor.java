package com.rest.project.processor;

import com.rest.project.controller.OrdenCustomController;
import com.rest.project.entity.Orden;
import static com.rest.project.enums.OrderStatus.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrdenProcessor implements RepresentationModelProcessor<EntityModel<Orden>> {

    @Override
    public EntityModel<Orden> process(EntityModel<Orden> model) {
        OrdenCustomController controller = methodOn(OrdenCustomController.class);

        if (valid(model.getContent().getOrderStatus(), PAID_FOR))
            model.add(linkTo(controller.payOrder(model.getContent().getId())).withRel(IanaLinkRelations.PAYMENT));
        if (valid(model.getContent().getOrderStatus(), CANCELED))
            model.add(linkTo(controller.payOrder(model.getContent().getId())).withRel(IanaLinkRelations.PAYMENT));
        if (valid(model.getContent().getOrderStatus(), CANCELED))
            model.add(linkTo(controller.payOrder(model.getContent().getId())).withRel(IanaLinkRelations.PAYMENT));
        return model;
    }
}
