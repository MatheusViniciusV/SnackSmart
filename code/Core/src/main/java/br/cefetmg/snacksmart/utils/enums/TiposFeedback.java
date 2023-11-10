/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.utils.enums;

/**
 *
 * @author marco
 */
public enum TiposFeedback {
    ERRO,
    COMENTARIO;
    
    public int toInt(TiposFeedback tipo) {
        return switch (tipo){
            case ERRO -> 0;
            case COMENTARIO -> 1;
            default -> 0;
        };
    }
    
    public static TiposFeedback fromString(String tipoStr ){
        tipoStr = tipoStr.replace(" ", "_"); 
        for (TiposFeedback tipo : TiposFeedback.values()) {
            if (tipo.toString().equalsIgnoreCase(tipoStr)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo não reconhecido: " + tipoStr);
    }
}
