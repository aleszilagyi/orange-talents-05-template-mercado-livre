package com.orangetalents.mercadolivre.config.anotacoes;

import com.orangetalents.mercadolivre.config.validators.NullOrValueExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {NullOrValueExistsValidator.class})
@Target(value = {ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NullOrValueExists {
    String message() default "{fieldName} fornecido não está cadastrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();
}
