package br.cefetmg.snacksmart.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidador {
    public enum TiposInput {
        INPUT_CPF("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"),
        INPUT_EMAIL("^[\\w\\.-]+@([\\w\\-]+\\.)+[\\w]{2,}+(\\.[\\w]{2,})?$"),
        INPUT_TELEFONE("\\(\\d{2}\\)\\d{9}");

        private final String expressao;

        TiposInput(String expressao) {
            this.expressao = expressao;
        }

        public String obterExpressao() {
            return expressao;
        }
    }

    static public boolean validaCPF(String cpf) {
        Pattern padrao = Pattern.compile(TiposInput.INPUT_CPF.obterExpressao());
        Matcher correspondencias = padrao.matcher(cpf);

        return correspondencias.find();
    }

    static public boolean validaEmail(String email) {
        Pattern padrao = Pattern.compile(TiposInput.INPUT_EMAIL.obterExpressao());
        Matcher correspondencias = padrao.matcher(email);

        return correspondencias.find();
    }

    static public boolean validaTelefone(String telefone) {
        Pattern padrao = Pattern.compile(TiposInput.INPUT_TELEFONE.obterExpressao());
        Matcher correspondencias = padrao.matcher(telefone);

        return correspondencias.find();
    }
}
