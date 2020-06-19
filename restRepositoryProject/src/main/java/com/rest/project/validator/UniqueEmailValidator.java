package com.rest.project.validator;

import com.rest.project.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private static final Logger log = LoggerFactory.getLogger(UniqueEmailValidator.class);

    @Autowired
    private UsuarioRepository usuarioRepo;

    private boolean existEmail=true;

    public UniqueEmailValidator() {
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try{
            usuarioRepo.findByEmail(s).ifPresent(usuario -> {existEmail = false;});
        }catch (NullPointerException e){
            log.error("Failed to validate email. " + e + " - "+ e.getMessage() +" - "+ e.toString());
        }
        return existEmail;
    }
}
