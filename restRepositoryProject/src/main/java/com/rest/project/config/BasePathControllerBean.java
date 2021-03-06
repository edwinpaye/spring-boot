package com.rest.project.config;

import com.rest.project.controller.AuthController;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BasePathControllerBean /*implements RepresentationModelProcessor<RepositoryLinksResource>*/ {

//    @Override
//    public RepositoryLinksResource process(RepositoryLinksResource model) {
//        return model.add(Link.of(
//                ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString()+"/auth",
//                LinkRelation.of("authentication")));
//    }

    @Bean
    public RepresentationModelProcessor<RepositoryLinksResource> linkProcesor(){
        return new RepresentationModelProcessor<RepositoryLinksResource>() {
            @Override
            public RepositoryLinksResource process(final RepositoryLinksResource model) {
                return model.add(WebMvcLinkBuilder.linkTo(AuthController.class).withRel(LinkRelation.of("authentication")));
            }
        };
    }
}
