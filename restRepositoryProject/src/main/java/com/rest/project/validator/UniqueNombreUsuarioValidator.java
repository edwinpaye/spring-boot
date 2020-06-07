package com.rest.project.validator;

import com.rest.project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNombreUsuarioValidator implements ConstraintValidator<UniqueNombreUsuario, String> {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public void initialize(UniqueNombreUsuario constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return usuarioRepo.existByNombreUsuario(s);
    }
}
