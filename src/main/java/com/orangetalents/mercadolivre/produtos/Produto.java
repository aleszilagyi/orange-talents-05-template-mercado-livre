package com.orangetalents.mercadolivre.produtos;

import com.orangetalents.mercadolivre.categorias.Categoria;
import com.orangetalents.mercadolivre.produtos.caracteristicas.Caracteristica;
import com.orangetalents.mercadolivre.produtos.imagens.ImagemProduto;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @ManyToOne
    @NotNull
    private Usuario usuario;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private int quantidade;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @ManyToOne
    @NotNull
    private Categoria categoria;
    @NotNull
    @ElementCollection
    private Set<@NotNull Caracteristica> caracteristicas = new HashSet<>();
    @CreationTimestamp
    private LocalDateTime momentoCriacao;
    @NotNull
    @ElementCollection
    private Set<ImagemProduto> imagensProduto = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, Usuario usuario, BigDecimal valor, int quantidade, String descricao, Categoria categoria, Set<@NotNull Caracteristica> listaCaracteristicas) {
        this.nome = nome;
        this.usuario = usuario;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = listaCaracteristicas;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<ImagemProduto> getImagensProduto() {
        return imagensProduto;
    }

    public LocalDateTime getMomentoCriacao() {
        return momentoCriacao;
    }

    public void setImagensProduto(Set<ImagemProduto> imagensProduto) {
        this.imagensProduto = imagensProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
