package com.orangetalents.mercadolivre.outrosSistemas.email;

import com.orangetalents.mercadolivre.compras.Compra;
import com.orangetalents.mercadolivre.compras.pagamento.Transacao;
import com.orangetalents.mercadolivre.produtos.perguntas.Pergunta;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmail {

    public void EnviaEmailPadraoPergunta(Pergunta pergunta) {
        String emailUsuarioProduto = pergunta.getProduto().getUsuario().getUsername();
        String assuntoPadrao = String.format("Nova pergunta em %s", pergunta.getProduto().getNome());
        String bodyMensagemPadrao = String.format("Olá %s\n" +
                        "\n" +
                        "O seu produto %s recebeu uma pergunta feita pelo %s",
                pergunta.getProduto().getUsuario().getUsername(), pergunta.getProduto().getNome(), pergunta.getUsuarioPergunta().getUsername());

        ModeloEmail email = new ModeloEmail(emailUsuarioProduto, assuntoPadrao, bodyMensagemPadrao);

        System.out.println("Email enviado!\n" + "Dados e-mail: \n" + email.toString());
    }

    public void enviaEmailPagamentoComSucesso(Compra compra) {
        String emailUsuarioProduto = compra.getComprador().getUsername();
        String assuntoPadrao = String.format("Pagamento confirmado - %s", compra.getProduto().getNome());
        String bodyMensagemPadrao = String.format("Olá %s\n" +
                "\n" +
                "Seu pagamento referente ao produto %s foi confirmado\n" +
                "\n" +
                "Produto: %s\n" +
                "Valor: %s\n" +
                "Data da compra: %s", compra.getComprador().getUsername(), compra.getProduto().getNome(), compra.getProduto().getNome(), compra.getValorMomentoCompra(), compra.getMomentoRegistroCompra());
    }

    public void enviaEmailFalhaPagamento(Compra compra) {
        String emailUsuarioProduto = compra.getComprador().getUsername();
        String assuntoPadrao = String.format("Falha no pagamento para %s", compra.getProduto().getNome());
        String bodyMensagemPadrao = String.format("Olá %s\n" +
                "\n" +
                "Houve uma falha no pagamento referente ao produto %s\n" +
                "\n" +
                "Segue link para nova tentativa de pagamento: %s", compra.getComprador().getUsername(), compra.getProduto().getNome(), compra.getFormaPagamento().getLink(compra.getId()));
        ModeloEmail email = new ModeloEmail(emailUsuarioProduto, assuntoPadrao, bodyMensagemPadrao);
        System.out.println("Email enviado com sucesso! \n" + "\n\n" + "Email: \n" + email);
    }
}
