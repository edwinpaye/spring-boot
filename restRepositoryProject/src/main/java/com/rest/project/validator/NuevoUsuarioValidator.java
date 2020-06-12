package com.rest.project.validator;

import com.rest.project.dto.NuevoUsuario;
import com.rest.project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateNuevoUsuarioValidator")
@PropertySource("classpath:messages.properties")
public class NuevoUsuarioValidator implements Validator {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private Environment env;

    @Override
    public boolean supports(Class<?> aClass) {
        return NuevoUsuario.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NuevoUsuario usuario = (NuevoUsuario) o;
        if (checkNotBlank(usuario.getNombreUsuario()))
            errors.rejectValue("nombreUsuario", "nombreUsuario.empty", "El nombreUsuario no deve estar vacio");
        if (checkNotBlank(usuario.getNombre()))
            errors.rejectValue("nombre", "nombre.empty", "El nombre no deve estar vacio");
        if (checkNotBlank(usuario.getEmail()))
            errors.rejectValue("email", "email.empty", "El email no deve estar vacio");
        if (checkNotBlank(usuario.getPassword()))
            errors.rejectValue("password", "password.empty", "El password no deve estar vacio");
        if (repository.findByNombreUsuario(usuario.getNombreUsuario()).isPresent())
            errors.rejectValue("nombreUsuario", "nombreUsuario.exist", env.getProperty("uniqueNombreUsuario"));
        if (repository.findByEmail(usuario.getEmail()).isPresent())
            errors.rejectValue("email", "email.exist", env.getProperty("uniqueEmail"));
    }

    private boolean checkNotBlank(String s){
        return (s == null || s.trim().length() == 0);
    }
}
