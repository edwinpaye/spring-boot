package com.rest.project.processor;

import com.rest.project.controller.OrdenCustomController;
import com.rest.project.entity.Orden;
import com.rest.project.enums.OrderStatus;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrdenProcessor implements RepresentationModelProcessor<EntityModel<Orden>> {

    @Override
    public EntityModel<Orden> process(EntityModel<Orden> model) {
        OrdenCustomController controller = methodOn(OrdenCustomController.class);
        if (OrderStatus.valid(model.getContent().getOrderStatus(), OrderStatus.PAID_FOR))
            model.add(linkTo(controller.payOrder(model.getContent().getId())).withRel(IanaLinkRelations.PAYMENT));
        if (OrderStatus.valid(model.getContent().getOrderStatus(), OrderStatus.CANCELED))
            model.add(linkTo(controller.cancelOrder(model.getContent().getId())).withRel(LinkRelation.of("cancel")));
        if (OrderStatus.valid(model.getContent().getOrderStatus(), OrderStatus.FULFILLED))
            model.add(linkTo(controller.fulfillOrder(model.getContent().getId())).withRel(LinkRelation.of("fulfill")));
        model.addIf(!model.hasLink(LinkRelation.of("self")), () ->
                linkTo(OrdenCustomController.class).slash(model.getContent().getId()).withSelfRel());
        return model;
    }
}
