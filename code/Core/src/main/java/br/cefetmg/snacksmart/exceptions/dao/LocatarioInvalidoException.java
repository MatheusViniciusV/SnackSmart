/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package br.cefetmg.snacksmart.exceptions.dao;

/**
 *
 * @author eloym
 */
public class LocatarioInvalidoException extends RuntimeException {

    /**
     * Creates a new instance of <code>LocatarioInvalidoException</code> without
     * detail message.
     */
    public LocatarioInvalidoException() {
    }

    /**
     * Constructs an instance of <code>LocatarioInvalidoException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public LocatarioInvalidoException(String msg) {
        super(msg);
    }
}
