package com.orangetalents.mercadolivre.outrosSistemas.ranking;

import com.orangetalents.mercadolivre.compras.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeraRanking {

    public void cria(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        RankingRequest request = new RankingRequest(compra.getId(), compra.getComprador().getId());
        restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
    }
}
