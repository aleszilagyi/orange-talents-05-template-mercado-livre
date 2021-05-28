package com.orangetalents.mercadolivre.produtos;

import com.orangetalents.mercadolivre.categorias.Categoria;
import com.orangetalents.mercadolivre.config.anotacoes.ValueExists;
import com.orangetalents.mercadolivre.config.anotacoes.VerificaListaValorDuplicado;
import com.orangetalents.mercadolivre.produtos.caracteristicas.Caracteristica;
import com.orangetalents.mercadolivre.produtos.caracteristicas.FormCaracteristicasRequest;
import com.orangetalents.mercadolivre.usuarios.Usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FormProdutoRequest {
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private int quantidade;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @ValueExists(fieldName = "id", domainClass = Categoria.class)
    @NotNull
    private Long idCategoria;
    @NotNull
    @Size(min = 3)
    @VerificaListaValorDuplicado
    private List<FormCaracteristicasRequest> listaCaracteristicas;

    public FormProdutoRequest(String nome, BigDecimal valor, int quantidade, String descricao, Long idCategoria, List<FormCaracteristicasRequest> listaCaracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.listaCaracteristicas = listaCaracteristicas;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public List<FormCaracteristicasRequest> getListaCaracteristicas() {
        return listaCaracteristicas;
    }

    public Produto converter(Categoria categoria, Usuario usuario) {
        Set<Caracteristica> set = listaCaracteristicas.stream().map(FormCaracteristicasRequest::converter).collect(Collectors.toSet());
        return new Produto(nome, usuario, valor, quantidade, descricao, categoria, set);
    }
}
