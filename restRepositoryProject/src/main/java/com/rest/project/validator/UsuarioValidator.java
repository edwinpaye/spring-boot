package com.rest.project.validator;

import com.rest.project.entity.Usuario;
import com.rest.project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateUsuarioValidator")
public class UsuarioValidator implements Validator {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Usuario.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Usuario usuario = (Usuario) o;
        if (checkNotBlank(usuario.getNombreUsuario()))
            errors.rejectValue("nombreUsuario", "nombreUsuario.empty");
        if (checkNotBlank(usuario.getNombre()))
            errors.rejectValue("nombre", "nombre.empty");
        if (checkNotBlank(usuario.getEmail()))
            errors.rejectValue("email", "email.empty");
        if (checkNotBlank(usuario.getPassword()))
            errors.rejectValue("password", "password.empty");
        if (repository.findByNombreUsuario(usuario.getNombreUsuario()).isPresent())
            errors.rejectValue("nombreUsuario", "nombreUsuario.exist");
        if (repository.findByEmail(usuario.getEmail()).isPresent())
            errors.rejectValue("email", "email.exist", "test");
    }

    private boolean checkNotBlank(String s){
        return (s == null || s.trim().length() == 0);
    }
}
