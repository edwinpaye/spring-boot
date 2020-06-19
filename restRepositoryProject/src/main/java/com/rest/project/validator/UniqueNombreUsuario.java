package com.rest.project.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNombreUsuarioValidator.class)
@NotBlank(message = "El nombreUsuario no deve estar vacio")
public @interface UniqueNombreUsuario {

//    String message() default "{com.rest.project.validator.UniqueNombreUsuario.message}";
    String message() default "other note nombre usuario";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
