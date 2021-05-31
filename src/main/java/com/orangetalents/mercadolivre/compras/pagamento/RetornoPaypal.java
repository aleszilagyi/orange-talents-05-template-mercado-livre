package com.orangetalents.mercadolivre.compras.pagamento;

import com.orangetalents.mercadolivre.compras.Compra;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPaypal {
    @NotBlank
    private String idTransacao;
    @NotNull
    @Range(min = 0, max = 1)
    private Integer status;

    public RetornoPaypal(String idTransacao, Integer status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Transacao converter(Compra compra) {
        StatusPagamento statusPagamento = normaliza();
        return new Transacao(statusPagamento, idTransacao, compra);
    }

    private StatusPagamento normaliza() {
        if (status == 0) return StatusPagamento.ERRO;
        return StatusPagamento.SUCESSO;
    }
}
