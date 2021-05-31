package com.orangetalents.mercadolivre.outrosSistemas.notasFiscais;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class NotaFiscalRequest {
    @NotNull
    private Long idComprador;
    @NotNull
    private UUID idVendedor;

    public NotaFiscalRequest(Long idComprador, UUID idVendedor) {
        this.idComprador = idComprador;
        this.idVendedor = idVendedor;
    }

    public Long getIdComprador() {
        return idComprador;
    }

    public UUID getIdVendedor() {
        return idVendedor;
    }
}
