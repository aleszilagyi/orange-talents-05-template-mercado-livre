package com.orangetalents.mercadolivre.compartilhado.validators;

import com.orangetalents.mercadolivre.compartilhado.anotacoes.VerificaDestinatarioEmail;
import com.orangetalents.mercadolivre.email.ModeloEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VerificaDestinatarioEmailValidator implements ConstraintValidator<VerificaDestinatarioEmail, ModeloEmail> {
    @Override
    public boolean isValid(ModeloEmail email, ConstraintValidatorContext constraintValidatorContext) {
        if (email.getDestinatario().equals("") && email.getCc().equals("")) {
            return false;
        }
        return true;
    }
}
