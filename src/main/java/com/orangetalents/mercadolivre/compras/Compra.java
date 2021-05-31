package com.orangetalents.mercadolivre.compras;

import com.orangetalents.mercadolivre.compras.pagamento.Transacao;
import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @NotNull
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();
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

    public void addTransacoes(Transacao transacao) {
        //fechar contrato para implementar um validador mais tarde
        Assert.isTrue(!this.transacoes.contains(transacao), "ops, parece que já existe uma transação igual a esta");
        this.transacoes.add(transacao);
    }

    private Set<Transacao> getTransacoesComSucesso() {
        return this.transacoes.stream().filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
    }

    public boolean isProcessadoComSucesso() {
        return !getTransacoesComSucesso().isEmpty();
    }
}
