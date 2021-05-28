package com.orangetalents.mercadolivre.produtos.imagens;

public class ImagemProdutoDto {
    public String link;

    public ImagemProdutoDto(ImagemProduto imagemProduto) {
        this.link = imagemProduto.getLink();
    }

    public String getLink() {
        return link;
    }
}
