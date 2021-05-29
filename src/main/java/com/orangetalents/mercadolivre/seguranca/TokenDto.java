package com.orangetalents.mercadolivre.seguranca;

public class TokenDto {
    private String jwt;
    private String tipo;

    public TokenDto(String jwt, String tipo) {
        this.jwt = jwt;
        this.tipo = tipo;
    }

    public String getJwt() {
        return jwt;
    }

    public String getTipo() {
        return tipo;
    }
}
