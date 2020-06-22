package com.rest.project.processor;

import com.rest.project.entity.Orden;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrdenProcessor implements RepresentationModelProcessor<EntityModel<Orden>> {

    @Override
    public EntityModel<Orden> process(EntityModel<Orden> model) {
        return null;
    }
}
