package br.cefetmg.snacksmart.exceptions.dto;

public class TelefoneInvalidoException extends ParametroInvalidoException{
    public TelefoneInvalidoException(String telefone) {
        super(String.format("Formato do telefone \"%s\" invalido. Deveria estar no formato (00)000000000", telefone));
    }
}
