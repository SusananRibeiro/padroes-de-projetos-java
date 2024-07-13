package com.padroes.framework.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static Map<String, Object> responseMapper(Object mensagens) {
        Map<String, Object> saida = new HashMap<>();
        saida.put("messages", mensagens);
        return saida;
    }
}
