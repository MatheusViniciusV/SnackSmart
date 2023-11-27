/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package br.cefetmg.snacksmart.exceptions.dto;

import br.cefetmg.snacksmart.exceptions.dto.ParametroInvalidoException;

/**
 *
 * @author eloym
 */
public class CPFInvalidoException extends ParametroInvalidoException {
    public CPFInvalidoException() {
        super("Formato do CPF invalido ou nulo");
    }
    
    public CPFInvalidoException(String msg) {
        super(msg);
    }
}
