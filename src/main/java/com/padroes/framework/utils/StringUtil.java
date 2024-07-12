package com.padroes.framework.utils;

public class StringUtil {

    public static boolean validarString(String entrada) {
        if(entrada == null || entrada == "") {
            return true;
        }
        return false;
    }
}
