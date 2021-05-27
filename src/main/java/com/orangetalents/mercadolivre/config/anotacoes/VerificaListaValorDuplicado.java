package com.orangetalents.mercadolivre.config.anotacoes;

import com.orangetalents.mercadolivre.config.validators.VerificaListaValorDuplicadoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {VerificaListaValorDuplicadoValidator.class})
@Target(value = {ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VerificaListaValorDuplicado {
    String message() default "a lista deve conter no mínimo 3 características com nomes distintos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
