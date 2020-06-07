package com.rest.project.validator;

import com.rest.project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

//    @Autowired
    private UsuarioRepository usuarioRepo;

    public UniqueEmailValidator(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) { }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !usuarioRepo.findByEmail(s).isPresent();
    }
}
