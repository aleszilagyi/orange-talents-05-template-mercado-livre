package com.orangetalents.mercadolivre.compras;

import java.net.URI;
import java.util.Locale;

public enum GatewayPagamento {
    PAYPAL("paypal"),
    PAGSEGURO("pagseguro");

    private String value;

    public URI getLink(Long id) {
        String metodoDePagamento = String.format("%s.com?buyerId=%s&redirectUrl=%s", this.toString().toLowerCase(Locale.ROOT), id.toString(), "url.retorno.a.definir.ainda");
        return URI.create(metodoDePagamento);
    }

    GatewayPagamento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
