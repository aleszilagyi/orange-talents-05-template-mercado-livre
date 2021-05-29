package com.orangetalents.mercadolivre.produtos.opinioes;

import java.time.LocalDateTime;

public class OpiniaoDto {
    private Long id;
    private Integer nota;
    private String titulo;
    private String descricao;
    private String usernameOpiniao;
    private LocalDateTime momentoCriacao;

    public OpiniaoDto(Opiniao opiniao) {
        this.id = opiniao.getId();
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.usernameOpiniao = opiniao.getUsuarioOpiniao().getUsername();
        this.momentoCriacao = opiniao.getMomentoCriacao();
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUsernameOpiniao() {
        return usernameOpiniao;
    }

    public LocalDateTime getMomentoCriacao() {
        return momentoCriacao;
    }
}
