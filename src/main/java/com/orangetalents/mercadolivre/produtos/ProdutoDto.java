package com.orangetalents.mercadolivre.produtos;

import com.orangetalents.mercadolivre.categorias.CategoriaDto;
import com.orangetalents.mercadolivre.produtos.caracteristicas.CaracteristicaDto;
import com.orangetalents.mercadolivre.produtos.imagens.ImagemProdutoDto;
import com.orangetalents.mercadolivre.produtos.opinioes.OpiniaoDto;
import com.orangetalents.mercadolivre.produtos.perguntas.PerguntaDto;
import com.orangetalents.mercadolivre.usuarios.UsuarioDto;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoDto {
    private Long id;
    private String nome;
    private UsuarioDto usuario;
    private BigDecimal valor;
    private Integer quantidade;
    private String descricao;
    private CategoriaDto categoria;
    private List<CaracteristicaDto> caracteristicas;
    private List<ImagemProdutoDto> imagensProdutoDtos;
    private List<PerguntaDto> listaPerguntas;
    private List<OpiniaoDto> listaOpinioes;
    private Integer quantidadeNotas;
    private double mediaNotas;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.usuario = new UsuarioDto(produto.getUsuario());
        this.valor = produto.getValor();
        this.quantidade = produto.getEstoque();
        this.descricao = produto.getDescricao();
        this.categoria = new CategoriaDto(produto.getCategoria());
        this.caracteristicas = produto.mapperParaList(produto.getCaracteristicas(), CaracteristicaDto::new);
        this.imagensProdutoDtos = produto.mapperParaList(produto.getImagensProduto(), ImagemProdutoDto::new);
        this.listaPerguntas = produto.mapperParaList(produto.getPerguntasAoProduto(), PerguntaDto::new);
        this.listaOpinioes = produto.mapperParaList(produto.getOpinioesDoProduto(), OpiniaoDto::new);
        this.quantidadeNotas = produto.getOpinioesDoProduto().size();
        this.mediaNotas = produto.retornaMediaNotas(produto.getOpinioesDoProduto());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public List<CaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }

    public List<ImagemProdutoDto> getImagensProdutoDtos() {
        return imagensProdutoDtos;
    }

    public List<PerguntaDto> getListaPerguntas() {
        return listaPerguntas;
    }

    public List<OpiniaoDto> getListaOpinioes() {
        return listaOpinioes;
    }

    public Integer getQuantidadeNotas() {
        return quantidadeNotas;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

}
