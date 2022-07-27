package com.neylandev.delivery.domain.anotations;


import com.neylandev.delivery.domain.validator.NameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {

    String message() default "O nome='${validatedValue}' não pode conter caracteres especiais ou números, ou conter 4 caracteres iguais em sequencia";
    Class[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
