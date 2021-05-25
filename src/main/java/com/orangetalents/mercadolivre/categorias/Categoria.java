package com.orangetalents.mercadolivre.categorias;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String nome;
    @ManyToOne(optional = true)
    private Categoria categoriaMae;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }
}
