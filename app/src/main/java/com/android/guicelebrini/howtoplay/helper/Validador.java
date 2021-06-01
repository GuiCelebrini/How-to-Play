package com.android.guicelebrini.howtoplay.helper;

public class Validador {

    public static Boolean verificarCampos(String emailInserido, String senhaInserida) {
        if (emailInserido.equals("") || senhaInserida.equals("")) {
            return false;
        }
        return true;
    }

}
