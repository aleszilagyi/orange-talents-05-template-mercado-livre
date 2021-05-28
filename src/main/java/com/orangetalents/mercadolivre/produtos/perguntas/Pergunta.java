package com.orangetalents.mercadolivre.produtos.perguntas;

import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 500)
    private String titulo;
    @NotNull
    @ManyToOne
    private Usuario usuarioPergunta;
    @NotNull
    @ManyToOne
    private Produto produto;
    @CreationTimestamp
    private LocalDateTime momentoCriacao;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, Usuario usuarioPergunta, Produto produto) {
        this.titulo = titulo;
        this.usuarioPergunta = usuarioPergunta;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getUsuarioPergunta() {
        return usuarioPergunta;
    }

    public Produto getProduto() {
        return produto;
    }

    public LocalDateTime getMomentoCriacao() {
        return momentoCriacao;
    }
}
