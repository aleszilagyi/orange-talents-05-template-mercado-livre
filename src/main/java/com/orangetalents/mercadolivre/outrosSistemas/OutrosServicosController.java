package com.orangetalents.mercadolivre.outrosSistemas;

import com.orangetalents.mercadolivre.outrosSistemas.notasFiscais.NotaFiscalRequest;
import com.orangetalents.mercadolivre.outrosSistemas.ranking.RankingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosServicosController {

    @PostMapping("/notas-fiscais")
    public void criaNotaFiscal(@RequestBody @Valid NotaFiscalRequest request) throws InterruptedException {
        System.out.println("Criando nota fiscal para " + request.getIdComprador() + " do comprador " + request.getIdVendedor());
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@RequestBody @Valid RankingRequest request) throws InterruptedException {
        System.out.println("Criando para " + request.getIdVendedor());
        Thread.sleep(150);
    }
}
