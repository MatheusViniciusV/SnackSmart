package br.cefetmg.snacksmart.exceptions.dto;

/**
 *
 * @author eloym
 */
public class NomeNuloException extends ParametroInvalidoException {
    public NomeNuloException() {
        super("Nome não pode ser nulo.");
    }
}
