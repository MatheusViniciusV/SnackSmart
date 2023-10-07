/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package br.cefetmg.snacksmart.exceptions.dto;

/**
 *
 * @author eloym
 */
public class CPFInvalidoException extends RuntimeException {

    /**
     * Creates a new instance of <code>CPFInvalidoException</code> without
     * detail message.
     */
    public CPFInvalidoException() {
    }

    /**
     * Constructs an instance of <code>CPFInvalidoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CPFInvalidoException(String msg) {
        super(msg);
    }
}
