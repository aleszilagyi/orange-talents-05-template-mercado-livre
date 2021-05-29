package com.orangetalents.mercadolivre.email;

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
}
