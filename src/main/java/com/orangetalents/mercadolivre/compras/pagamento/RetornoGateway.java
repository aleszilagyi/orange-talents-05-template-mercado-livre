package com.orangetalents.mercadolivre.compras.pagamento;

import com.orangetalents.mercadolivre.compras.Compra;

public interface RetornoGateway {
    public Transacao converter(Compra compra);
}
