package com.orangetalents.mercadolivre.users;

import java.util.UUID;

public class UsuarioDto {
    private UUID id;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
    }

    public UUID getId() {
        return id;
    }
}
