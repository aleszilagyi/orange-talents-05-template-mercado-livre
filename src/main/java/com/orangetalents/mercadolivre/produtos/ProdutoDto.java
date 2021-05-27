package com.orangetalents.mercadolivre.produtos;

import com.orangetalents.mercadolivre.categorias.CategoriaDto;
import com.orangetalents.mercadolivre.usuarios.UsuarioDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {
    private Long id;
    private String nome;
    private UsuarioDto usuario;
    private BigDecimal valor;
    private int quantidade;
    private String descricao;
    private CategoriaDto categoria;
    private List<CaracteristicaDto> caracteristicas;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.usuario = new UsuarioDto(produto.getUsuario());
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.descricao = produto.getDescricao();
        this.categoria = new CategoriaDto(produto.getCategoria());
        this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaDto::new).collect(Collectors.toList());
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

    public int getQuantidade() {
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
}
