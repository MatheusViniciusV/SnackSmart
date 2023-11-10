package br.cefetmg.snacksmart.exceptions.dao;

public class FormatoArquivoInvalidoException extends RuntimeException{
    public FormatoArquivoInvalidoException() {
    }
    public FormatoArquivoInvalidoException(String msg) {
        super(msg);
    }
}