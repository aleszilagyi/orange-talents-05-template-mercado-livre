package com.orangetalents.mercadolivre.produtos.email;

import com.orangetalents.mercadolivre.config.anotacoes.VerificaDestinatarioEmail;

@VerificaDestinatarioEmail
public class ModeloEmail {
    private String destinatario;
    private String cc;
    private String cco;
    private String assunto;
    private String body;

    public ModeloEmail(String destinatario, String cc, String cco, String assunto, String body) {
        if (destinatario == null) {
            this.destinatario = "";
        } else {
            this.destinatario = destinatario;
        }
        if (cc == null) {
            this.cc = "";
        } else {
            this.cc = cc;
        }
        if (cco == null) {
            this.cco = "";
        } else {
            this.cco = cco;
        }
        if (assunto == null) {
            this.assunto = "";
        } else {
            this.assunto = assunto;
        }
        if (body == null) {
            this.body = "";
        } else {
            this.body = body;
        }
    }

    public ModeloEmail(String destinatario, String assunto, String body) {
        if (destinatario == null) {
            this.destinatario = "";
        } else {
            this.destinatario = destinatario;
        }
        if (assunto == null) {
            this.assunto = "";
        } else {
            this.assunto = assunto;
        }
        if (body == null) {
            this.body = "";
        } else {
            this.body = body;
        }
    }

    public ModeloEmail(String cc, String cco, String assunto, String body) {
        if (cc == null) {
            this.cc = "";
        } else {
            this.cc = cc;
        }
        if (cco == null) {
            this.cco = "";
        } else {
            this.cco = cco;
        }
        if (assunto == null) {
            this.assunto = "";
        } else {
            this.assunto = assunto;
        }
        if (body == null) {
            this.body = "";
        } else {
            this.body = body;
        }
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getCc() {
        return cc;
    }

    public String getCco() {
        return cco;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return String.format("Para: %s" +
                "\n" +
                "Assunto: %s" +
                "\n" +
                "Corpo: \n" +
                "%s", destinatario, assunto, body);
    }
}
