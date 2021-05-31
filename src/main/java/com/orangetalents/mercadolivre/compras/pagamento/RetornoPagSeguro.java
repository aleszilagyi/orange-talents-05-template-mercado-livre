package com.orangetalents.mercadolivre.compras.pagamento;

import com.orangetalents.mercadolivre.compras.Compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagSeguro implements RetornoGateway{
    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusPagSeguro status;

    public RetornoPagSeguro(String idTransacao, StatusPagSeguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Transacao converter(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
