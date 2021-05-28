package com.orangetalents.mercadolivre.produtos.perguntas;

import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.usuarios.Usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FormPerguntaRequest {
    @NotBlank
    @Size(max = 500)
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta converter(Usuario usuario, Produto produto) {
        return new Pergunta(titulo, usuario, produto);
    }
}
