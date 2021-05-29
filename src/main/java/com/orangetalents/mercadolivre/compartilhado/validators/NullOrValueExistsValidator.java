package com.orangetalents.mercadolivre.compartilhado.validators;

import com.orangetalents.mercadolivre.compartilhado.anotacoes.NullOrValueExists;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NullOrValueExistsValidator implements ConstraintValidator<NullOrValueExists, Object> {
    private String domainAttribute;
    private Class<?> aClass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(NullOrValueExists params) {
        domainAttribute = params.fieldName();
        aClass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null) return true;
        Query query = manager.createQuery("select 1 from " + aClass.getName() + " where " + domainAttribute + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado(a) mais de um(a) " + aClass + " com o atributo " + domainAttribute + " = " + value);

        return list.size() == 1;
    }
}