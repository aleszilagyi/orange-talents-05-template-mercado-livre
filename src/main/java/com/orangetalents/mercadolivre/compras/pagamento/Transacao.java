package com.orangetalents.mercadolivre.compras.pagamento;

import com.orangetalents.mercadolivre.compras.Compra;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusPagamento status;
    @NotBlank
    private String idTransacao;
    @ManyToOne
    @NotNull
    @Valid
    private Compra compra;
    @CreationTimestamp
    private LocalDateTime momentoCriacao;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusPagamento status, String idTransacao, Compra compra) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.compra = compra;
    }

    public Long getId() {
        return id;
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusPagamento.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return status == transacao.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}
