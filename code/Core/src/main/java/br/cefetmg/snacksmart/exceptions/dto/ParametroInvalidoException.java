package br.cefetmg.snacksmart.exceptions.dto;

public class ParametroInvalidoException extends RuntimeException {
    public ParametroInvalidoException() {
    }

    public ParametroInvalidoException(String msg) {
        super(msg);
    }
}
