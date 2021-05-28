package com.orangetalents.mercadolivre.produtos;

public class ImagemProdutoDto {
    private Long id;

    public ImagemProdutoDto(Produto produto) {
        this.id = produto.getId();
    }

    public Long getId() {
        return id;
    }
}
