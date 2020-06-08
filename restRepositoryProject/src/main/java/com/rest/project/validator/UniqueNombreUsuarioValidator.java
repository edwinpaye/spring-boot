package com.rest.project.validator;

import com.rest.project.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNombreUsuarioValidator implements ConstraintValidator<UniqueNombreUsuario, String> {

    private static final Logger log = LoggerFactory.getLogger(UniqueNombreUsuarioValidator.class);

    @Autowired
    private UsuarioRepository usuarioRepo;

    private boolean existNombreUsuario = false;

    public UniqueNombreUsuarioValidator() {
    }

    @Override
    public void initialize(UniqueNombreUsuario constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            usuarioRepo.findByNombreUsuario(s).ifPresent(usuario -> {existNombreUsuario = true;});
        }catch (NullPointerException e){
            log.error("Failed to validate nombreUsuario. " + e +" - "+e.getMessage()+" - "+e.getCause().getLocalizedMessage());
        }
        return existNombreUsuario;
    }
}
