package com.orangetalents.mercadolivre.produtos.caracteristicas;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class FormCaracteristicasRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public FormCaracteristicasRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Caracteristica converter() {
        return new Caracteristica(nome, descricao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormCaracteristicasRequest)) return false;
        FormCaracteristicasRequest that = (FormCaracteristicasRequest) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
