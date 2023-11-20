package br.cefetmg.snacksmart.exceptions.services;

/**
 *
 * @author eloym
 */
public class SenhaInvalidaTamanhoException extends RuntimeException {

    /**
     * Creates a new instance of <code>SenhaInvalidaTamanho</code> without
     * detail message.
     */
    public SenhaInvalidaTamanhoException() {
    }

    /**
     * Constructs an instance of <code>SenhaInvalidaTamanho</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SenhaInvalidaTamanhoException(String msg) {
        super(msg);
    }
}
