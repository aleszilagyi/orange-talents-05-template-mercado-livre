package com.orangetalents.mercadolivre.produtos.imagens;

import org.hibernate.validator.constraints.URL;

import javax.persistence.Embeddable;


@Embeddable
public class ImagemProduto {

    @URL
    private String link;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
