package com.orangetalents.mercadolivre.compras;

import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.usuarios.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraFormRequest {
    private GatewayPagamento gatewayPagamento;
    @NotNull
    @Positive
    private Integer quantidade;

    public CompraFormRequest(GatewayPagamento formaPagamento, Integer quantidade) {
        this.gatewayPagamento = formaPagamento;
        this.quantidade = quantidade;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Compra converter(Usuario usuario, Produto produto) {
        return new Compra(gatewayPagamento, produto, quantidade, usuario);
    }
}
