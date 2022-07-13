package com.algaworks.algalog.domain.anotations;


import com.algaworks.algalog.domain.validator.TelephoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = TelephoneValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Telephone {

    String message() default "O telefone='${validatedValue}' é inválido";
    Class[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
