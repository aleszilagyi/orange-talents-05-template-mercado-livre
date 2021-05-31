package com.orangetalents.mercadolivre.outrosSistemas.ranking;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class RankingRequest {
    @NotNull
    private Long idComprador;
    @NotNull
    private UUID idVendedor;

    public RankingRequest(Long idComprador, UUID idVendedor) {
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
