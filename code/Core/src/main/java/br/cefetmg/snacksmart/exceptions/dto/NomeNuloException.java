/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
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
