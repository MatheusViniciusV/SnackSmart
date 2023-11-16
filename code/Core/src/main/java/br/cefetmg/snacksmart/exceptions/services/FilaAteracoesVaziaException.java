/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package br.cefetmg.snacksmart.exceptions.services;

/**
 *
 * @author eloym
 */
public class FilaAteracoesVaziaException extends RuntimeException {

    /**
     * Creates a new instance of <code>FilaAteracoesVaziaException</code>
     * without detail message.
     */
    public FilaAteracoesVaziaException() {
    }

    /**
     * Constructs an instance of <code>FilaAteracoesVaziaException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public FilaAteracoesVaziaException(String msg) {
        super(msg);
    }
}
