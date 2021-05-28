package com.orangetalents.mercadolivre.produtos.perguntas;

import com.orangetalents.mercadolivre.produtos.ProdutoDto;

import java.time.LocalDateTime;

public class PerguntaDto {
    private Long id;
    private String titulo;
    private LocalDateTime momentoCriacao;
    private String usernamePergunta;
    private ProdutoDto produto;

    public PerguntaDto(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.titulo = pergunta.getTitulo();
        this.momentoCriacao = pergunta.getMomentoCriacao();
        this.usernamePergunta = pergunta.getUsuarioPergunta().getUsername();
        this.produto = new ProdutoDto(pergunta.getProduto());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getMomentoCriacao() {
        return momentoCriacao;
    }

    public String getUsernamePergunta() {
        return usernamePergunta;
    }

    public ProdutoDto getProduto() {
        return produto;
    }
}
