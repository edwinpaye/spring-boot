package com.rest.project.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@NotBlank(message = "El email no deve estar vacio")
@Email(message = "Email no valido")
public @interface UniqueEmail {

//    String message() default "{com.rest.project.validator.UniqueEmail.message}";
    String message() default "other note";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
