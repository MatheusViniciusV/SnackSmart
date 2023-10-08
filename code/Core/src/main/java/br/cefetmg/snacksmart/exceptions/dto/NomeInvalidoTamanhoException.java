package br.cefetmg.snacksmart.exceptions.dto;

/**
 *
 * @author Arthur Milagres
 */
public class NomeInvalidoTamanhoException extends RuntimeException { 
    
    public NomeInvalidoTamanhoException(String msg) {
        super(msg);
    }
}
