package com.orangetalents.mercadolivre.compras;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Locale;

public enum GatewayPagamento {
    PAYPAL("paypal"),
    PAGSEGURO("pagseguro");

    private String value;

    public URI getLink(Long id) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080");
        String redirLink = uriComponentsBuilder.path("/retorno-" + this.toString().toLowerCase(Locale.ROOT) + "/" + id).buildAndExpand().toString();
        String initialLink = "/" + this.toString().toLowerCase(Locale.ROOT) + ".com?buyerId=" + id + "&redirectUrl=";
        return URI.create(initialLink + redirLink);
    }

    GatewayPagamento(String value) {
        this.value = value.toLowerCase(Locale.ROOT);
    }

    public String getValue() {
        return value.toLowerCase(Locale.ROOT);
    }
}
