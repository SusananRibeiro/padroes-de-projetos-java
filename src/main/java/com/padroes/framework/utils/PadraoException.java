package com.padroes.framework.utils;

import java.util.ArrayList;
import java.util.List;

public class PadraoException extends Exception {
    private List<String> mensagens = new ArrayList<>();
    public PadraoException(String mensagem) {

        super(mensagem);
    }

    public PadraoException(List<String> mensagens) {
        this.mensagens = mensagens;
    }

    public List<String> getMensagens() {
        if(mensagens.isEmpty()) {
            mensagens.add(super.getMessage());
        }
        return mensagens;
    }

    public String getMensagem() {
        if(mensagens.isEmpty()) {
            return super.getMessage();
        }
        return mensagens.toString();
    }

}
