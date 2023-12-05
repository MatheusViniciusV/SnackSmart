package br.cefetmg.snacksmart.exceptions.dto;

public class EmailInvalidoException extends ParametroInvalidoException {
    public EmailInvalidoException() {
        super("Email invalido");
    }
}
