package com.orangetalents.mercadolivre.produtos;

public class CaracteristicaDto {
    private String nome;
    private String descricao;

    public CaracteristicaDto(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
