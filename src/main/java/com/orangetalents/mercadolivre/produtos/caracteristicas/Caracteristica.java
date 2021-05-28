package com.orangetalents.mercadolivre.produtos.caracteristicas;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Embeddable
public class Caracteristica {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    @Deprecated
    public Caracteristica() {
    }

    public Caracteristica(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Caracteristica)) return false;
        Caracteristica that = (Caracteristica) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
