package com.orangetalents.mercadolivre.produtos;

import com.orangetalents.mercadolivre.categorias.Categoria;
import com.orangetalents.mercadolivre.categorias.CategoriaRepository;
import com.orangetalents.mercadolivre.config.anotacoes.ValueExists;
import com.orangetalents.mercadolivre.config.anotacoes.VerificaListaValorDuplicado;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import com.orangetalents.mercadolivre.usuarios.UsuarioRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FormProdutoRequest {
    @NotBlank
    private String nome;
    private String username;
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
    private List<FormCaracteristicasRequest> listaCaracteristicas = new ArrayList<>();

    public FormProdutoRequest(String nome, BigDecimal valor, int quantidade, String descricao, Long idCategoria, List<FormCaracteristicasRequest> listaCaracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.listaCaracteristicas.addAll(listaCaracteristicas);
    }

    public String getNome() {
        return nome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
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

    public Produto converter(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository) {
        Categoria categoria = categoriaRepository.getById(idCategoria);
        Usuario usuario = usuarioRepository.findByUsername(username).get();
        return new Produto(nome, usuario, valor, quantidade, descricao, categoria, listaCaracteristicas);
    }
}
