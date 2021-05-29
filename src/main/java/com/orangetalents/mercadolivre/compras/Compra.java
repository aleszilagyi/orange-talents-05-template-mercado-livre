package com.orangetalents.mercadolivre.compras;

import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento formaPagamento;
    @ManyToOne
    @NotNull
    private Produto produto;
    @Positive
    @NotNull
    private Integer quantidade;
    @ManyToOne
    @NotNull
    private Usuario comprador;
    @NotNull
    private BigDecimal valorMomentoCompra;
    @Enumerated(EnumType.STRING)
    private StatusCompra status = StatusCompra.INICIADO;
    @CreationTimestamp
    private LocalDateTime momentoRegistroCompra;

    @Deprecated
    public Compra() {
    }

    public Compra(GatewayPagamento formaPagamento, Produto produto, Integer quantidade, Usuario comprador) {
        this.formaPagamento = formaPagamento;
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.valorMomentoCompra = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public GatewayPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public BigDecimal getValorMomentoCompra() {
        return valorMomentoCompra;
    }

    public LocalDateTime getMomentoRegistroCompra() {
        return momentoRegistroCompra;
    }
}
