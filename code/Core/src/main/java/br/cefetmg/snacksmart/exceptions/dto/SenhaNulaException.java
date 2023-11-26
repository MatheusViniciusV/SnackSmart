package br.cefetmg.snacksmart.exceptions.dto;

public class SenhaNulaException extends ParametroInvalidoException{
    public SenhaNulaException() {
        super("Senha nula não é permitida.");
    }
}
