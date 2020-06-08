package com.rest.project.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class ValidatorEventRegister implements InitializingBean {

    @Autowired
    ValidatingRepositoryEventListener listener;

    @Autowired
    private Map<String, Validator> validators;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<String> events = Arrays.asList("beforeCreate");
        for (Map.Entry<String,Validator> entry : validators.entrySet()){
            events.stream().filter(s -> entry.getKey().startsWith(s))
                    .findFirst().ifPresent(s -> listener.addValidator(s, entry.getValue()));
        }
    }
}