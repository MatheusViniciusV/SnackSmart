package br.cefetmg.snacksmart.utils.enums;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

/**
 *
 * @author eloym
 */
public enum TipoUsuario {
    LOCADOR,
    LOCATARIO,
    NAO_CADASTRADO;
    
    public int toInt(TipoUsuario usuario) {
        int valor = 0;
        
        switch (usuario) {
            case LOCADOR:
                valor = 0;
            case LOCATARIO: 
                valor = 1;
        }
        
        return valor; 
    }
}
