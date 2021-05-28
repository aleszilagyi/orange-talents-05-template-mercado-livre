package com.orangetalents.mercadolivre.config.anotacoes;

import com.orangetalents.mercadolivre.config.validators.VerificaDestinatarioEmailValidator;
import com.orangetalents.mercadolivre.config.validators.VerificaListaValorDuplicadoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {VerificaDestinatarioEmailValidator.class})
@Target(value = {ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VerificaDestinatarioEmail {
    String message() default "o e-mail deve conter pelo menos 1 destinatario ou cc";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
