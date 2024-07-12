package com.padroes.framework.utils;

import java.util.ArrayList;
import java.util.List;

public class Excecao extends Exception {
    private List<String> mensagens = new ArrayList<>();
    public Excecao(String mensagem) {
        super(mensagem);
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
