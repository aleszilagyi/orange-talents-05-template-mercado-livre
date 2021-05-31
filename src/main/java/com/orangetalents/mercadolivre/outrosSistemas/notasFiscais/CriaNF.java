package com.orangetalents.mercadolivre.outrosSistemas.notasFiscais;

import com.orangetalents.mercadolivre.compras.Compra;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CriaNF {

    public void cria(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        NotaFiscalRequest request = new NotaFiscalRequest(compra.getId(), compra.getComprador().getId());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }
}
