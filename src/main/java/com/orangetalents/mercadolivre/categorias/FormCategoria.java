package com.orangetalents.mercadolivre.categorias;

import com.orangetalents.mercadolivre.config.anotacoes.NullOrValueExists;
import com.orangetalents.mercadolivre.config.anotacoes.UniqueValue;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class FormCategoria {
    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;
    @NullOrValueExists(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaMaeId;

    public FormCategoria(String nome, Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }

    public Categoria converter(Categoria categoria) {

        return new Categoria(nome, categoria);
    }
}
