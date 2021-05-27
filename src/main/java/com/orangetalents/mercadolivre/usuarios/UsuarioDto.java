package com.orangetalents.mercadolivre.usuarios;

import java.util.UUID;

public class UsuarioDto {
    private UUID id;
    private String nome;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getUsername();
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
