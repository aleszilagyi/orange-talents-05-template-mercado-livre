package com.orangetalents.mercadolivre.produtos.opinioes;

public class OpiniaoDto {
    private Long id;
    private int nota;
    private String titulo;
    private String descricao;
    private Long produtoId;
    private String usernameOpiniao;

    public OpiniaoDto(Opiniao opiniao) {
        this.id = opiniao.getId();
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.produtoId = opiniao.getProduto().getId();
        this.usernameOpiniao = opiniao.getUsuarioOpiniao().getUsername();
    }

    public Long getId() {
        return id;
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getUsernameOpiniao() {
        return usernameOpiniao;
    }
}
