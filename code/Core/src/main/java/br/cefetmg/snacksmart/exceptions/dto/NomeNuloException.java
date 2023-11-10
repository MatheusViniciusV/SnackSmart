package br.cefetmg.snacksmart.exceptions.dto;

/**
 *
 * @author eloym
 */
public class NomeNuloException extends RuntimeException {

    /**
     * Creates a new instance of <code>NomeNuloException</code> without detail
     * message.
     */
    public NomeNuloException() {
    }

    /**
     * Constructs an instance of <code>NomeNuloException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NomeNuloException(String msg) {
        super(msg);
    }
}
