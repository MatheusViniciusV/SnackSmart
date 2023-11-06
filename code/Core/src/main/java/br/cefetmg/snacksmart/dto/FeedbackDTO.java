/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.dto;
import br.cefetmg.snacksmart.utils.enums.TiposFeedback;

/**
 *
 * @author marco
 */
public class FeedbackDTO {
    private int codigo;
    private String autor, recepitor, conteudo;
    private TiposFeedback tipo;
    
    public FeedbackDTO(int codigo,String autor,String recepitor, String conteudo, TiposFeedback tipo){
        this.codigo = codigo;
        this.autor = autor;
        this.recepitor = recepitor;
        this.conteudo = conteudo;
        this.tipo = tipo;
    }
    public FeedbackDTO(){
        this.codigo = 0;
        this.autor = null;
        this.recepitor = null;
        this.conteudo = null;
        this.tipo = null;
    }
    public int getCodigo() {
        return codigo;
    }
    public String getAutor(){
        return autor;
    }
    
    public String getRecepitor(){
        return recepitor;
    }
    
    public String getConteudo(){
        return conteudo;
    }
    
    public TiposFeedback getTipo(){
        return tipo;
    }
    
    public void setAutor(String autor){
        this.autor = autor;
    }
    
    public void setRecepitor(String recepitor){
        this.recepitor = recepitor;
    }
    
    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }
    
    public void setTipo(TiposFeedback tipo){
        this.tipo = tipo;
    }
    
}
