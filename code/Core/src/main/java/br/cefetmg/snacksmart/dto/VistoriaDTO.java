/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.dto;

/**
 *
 * @author VictorN77
 */
public class VistoriaDTO {
    
    private String data, descricao;
    
    public VistoriaDTO(String desc, String dat){
        
        this.descricao = desc;
        this.data = dat;
        
    }
    public String getData(){
    
        return data;
    }
    public String getDescricao(){
    
        return descricao;
    }
}
