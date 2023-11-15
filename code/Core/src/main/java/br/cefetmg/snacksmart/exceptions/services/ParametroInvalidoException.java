package br.cefetmg.snacksmart.exceptions.services;

public class ParametroInvalidoException extends RuntimeException {
    /**
     * Creates a new instance of <code>SenhaInvalidaTamanho</code> without
     * detail message.
     */
    public ParametroInvalidoException() {
    }

    /**
     * Constructs an instance of <code>SenhaInvalidaTamanho</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ParametroInvalidoException(String msg) {
        super(msg);
    }
}
