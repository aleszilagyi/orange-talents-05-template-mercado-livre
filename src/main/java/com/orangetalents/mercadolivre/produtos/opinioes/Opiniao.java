package com.orangetalents.mercadolivre.produtos.opinioes;

import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Opiniao implements Comparable<Opiniao> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;
    @NotBlank
    @Size(max = 100)
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;
    @ManyToOne
    @NotNull
    private Produto produto;
    @ManyToOne
    @NotNull
    private Usuario usuarioOpiniao;
    @CreationTimestamp
    private LocalDateTime momentoCriacao;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(int nota, String titulo, String descricao, Produto produto, Usuario usuarioOpiniao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuarioOpiniao = usuarioOpiniao;
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

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuarioOpiniao() {
        return usuarioOpiniao;
    }

    public LocalDateTime getMomentoCriacao() {
        return momentoCriacao;
    }

    @Override
    public int compareTo(Opiniao o) {
        return this.nota.compareTo(o.nota);
    }
}
