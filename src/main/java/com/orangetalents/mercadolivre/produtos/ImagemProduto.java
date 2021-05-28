package com.orangetalents.mercadolivre.produtos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private Produto produto;
    private String link;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(Produto produto, String link) {
        this.produto = produto;
        this.link = link;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getLink() {
        return link;
    }
}
