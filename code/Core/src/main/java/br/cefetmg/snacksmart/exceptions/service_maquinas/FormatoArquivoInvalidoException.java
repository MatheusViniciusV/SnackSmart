package br.cefetmg.snacksmart.exceptions.service_maquinas;

public class FormatoArquivoInvalidoException extends RuntimeException{
    public FormatoArquivoInvalidoException() {
    }
    public FormatoArquivoInvalidoException(String msg) {
        super(msg);
    }
}