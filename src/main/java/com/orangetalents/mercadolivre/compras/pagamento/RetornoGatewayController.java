package com.orangetalents.mercadolivre.compras.pagamento;

import com.orangetalents.mercadolivre.compras.Compra;
import com.orangetalents.mercadolivre.outrosSistemas.email.EnviaEmail;
import com.orangetalents.mercadolivre.outrosSistemas.notasFiscais.CriaNF;
import com.orangetalents.mercadolivre.outrosSistemas.ranking.GeraRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping
public class RetornoGatewayController {
    @Autowired
    private EnviaEmail enviaEmail;
    @Autowired
    private CriaNF criaNF;
    @Autowired
    private GeraRanking geraRanking;
    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity registraPagSeguro(@RequestBody @Valid RetornoPagSeguro retorno, @PathVariable("id") Long idCompra) {
        return verifiaERegistraTransacao(idCompra, retorno);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity registraPaypal(@RequestBody @Valid RetornoPagSeguro retorno, @PathVariable("id") Long idCompra) {
        return verifiaERegistraTransacao(idCompra, retorno);
    }

    private ResponseEntity verifiaERegistraTransacao(Long idCompra, RetornoGateway retorno) {
        Compra compra = entityManager.find(Compra.class, idCompra);
        if (compra == null) {
            return ResponseEntity.notFound().build();
        }

        Transacao transacao = retorno.converter(compra);
        compra.addTransacoes(transacao);

        entityManager.merge(compra);
        entityManager.flush();
        if (compra.isProcessadoComSucesso()) {
            criaNF.cria(compra);
            geraRanking.cria(compra);
            enviaEmail.enviaEmailPagamentoComSucesso(compra);
            return ResponseEntity.ok().build();
        }
        enviaEmail.enviaEmailFalhaPagamento(compra);
        return ResponseEntity.badRequest().build();
    }
}
