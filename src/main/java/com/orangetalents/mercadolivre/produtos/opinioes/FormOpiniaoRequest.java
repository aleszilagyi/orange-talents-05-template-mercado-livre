package com.orangetalents.mercadolivre.produtos.opinioes;

import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.usuarios.Usuario;

import javax.validation.constraints.*;

public class FormOpiniaoRequest {
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

    public FormOpiniaoRequest(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
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

    public Opiniao converter(Produto produto, Usuario usuario) {
        return new Opiniao(nota, titulo, descricao, produto, usuario);
    }
}
