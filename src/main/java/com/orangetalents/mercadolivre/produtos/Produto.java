package com.orangetalents.mercadolivre.produtos;

import com.orangetalents.mercadolivre.categorias.Categoria;
import com.orangetalents.mercadolivre.produtos.caracteristicas.Caracteristica;
import com.orangetalents.mercadolivre.produtos.imagens.ImagemProduto;
import com.orangetalents.mercadolivre.produtos.opinioes.Opiniao;
import com.orangetalents.mercadolivre.produtos.perguntas.Pergunta;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.BindException;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    @Min(0)
    private Integer estoque;
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
    @OneToMany(mappedBy = "produto", cascade = CascadeType.REFRESH)
    @ElementCollection
    @OrderBy("momentoCriacao desc")
    private SortedSet<Pergunta> perguntasAoProduto = new TreeSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.REFRESH)
    @ElementCollection
    @OrderBy("nota asc")
    private SortedSet<Opiniao> opinioesDoProduto = new TreeSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, Usuario usuario, BigDecimal valor, int estoque, String descricao, Categoria categoria, Set<@NotNull Caracteristica> listaCaracteristicas) {
        this.nome = nome;
        this.usuario = usuario;
        this.valor = valor;
        this.estoque = estoque;
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

    public int getEstoque() {
        return estoque;
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

    public SortedSet<Pergunta> getPerguntasAoProduto() {
        return perguntasAoProduto;
    }

    public SortedSet<Opiniao> getOpinioesDoProduto() {
        return opinioesDoProduto;
    }

    public LocalDateTime getMomentoCriacao() {
        return momentoCriacao;
    }

    public void setImagensProduto(Set<ImagemProduto> imagensProduto) {
        this.imagensProduto.addAll(imagensProduto);
    }

    public <T, D> List<T> mapperParaList(Collection<D> originalCollectionObject, Function<D, T> funcaoMap) {
        return originalCollectionObject.stream().map(funcaoMap).collect(Collectors.toList());
    }

    public double retornaMediaNotas(Collection<Opiniao> lista) {
        return lista.stream().mapToDouble(Opiniao::getNota).average().orElse(0);
    }

    public boolean abateQuantidade(@Positive Integer quantidadeComprada) throws BindException {
        if (estoque >= quantidadeComprada) {
            this.estoque -= quantidadeComprada;
            return true;
        } else {
            BindException exception = new BindException(estoque, "compraFormReques");
            exception.reject(null, "ops, parece que não há estoque suficiente para completar a compra");
            throw exception;
        }
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
