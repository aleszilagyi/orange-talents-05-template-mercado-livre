package com.orangetalents.mercadolivre.config.validators;

import com.orangetalents.mercadolivre.config.anotacoes.VerificaListaValorDuplicado;
import com.orangetalents.mercadolivre.produtos.FormCaracteristicasRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VerificaListaValorDuplicadoValidator implements ConstraintValidator<VerificaListaValorDuplicado, List<FormCaracteristicasRequest>> {

    @Override
    public boolean isValid(List<FormCaracteristicasRequest> list, ConstraintValidatorContext constraintValidatorContext) {
        Set<String> set = new HashSet<>();
        for (FormCaracteristicasRequest formCaracteristicasRequest : list) {
            if (!set.add(formCaracteristicasRequest.getNome())) return false;
        }
        return true;
    }
}
