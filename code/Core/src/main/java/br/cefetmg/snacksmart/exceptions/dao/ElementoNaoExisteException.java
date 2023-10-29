package br.cefetmg.snacksmart.exceptions.dao;

/**
 *
 * @author eloym
 */
public class ElementoNaoExisteException extends RuntimeException {
    public ElementoNaoExisteException() {
    }

    public ElementoNaoExisteException(String msg) {
        super(msg);
    }
}
