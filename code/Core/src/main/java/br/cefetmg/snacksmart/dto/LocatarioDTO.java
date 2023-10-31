/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.dto;

/**
 *
 * @author eloym
 */
public class LocatarioDTO {
    private String nome; 
    public LocatarioDTO(String nome){
        this.nome = nome;
    }
    public LocatarioDTO(){
        
    }
    public String getCPF() {
        return null;
    }
    
    public String getNome() {
        return nome;
    }
}
