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
    private String titulo, mensagem;
    private TiposFeedback solicitacao;
    
    public FeedbackDTO(int codigo, String titulo,String mensagem, TiposFeedback solicitacao){
        this.codigo = codigo;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.solicitacao = solicitacao;
    }
    public FeedbackDTO(){
        this.titulo = null;
        this.mensagem = null;
        this.solicitacao = null;
    }
    public int getCodigo() {
        return codigo;
    }
    public String getTitulo(){
        return titulo;
    }
    
    public String getMensagem(){
        return mensagem;
    }
    
    public TiposFeedback getTipoFeedback(){
        return solicitacao;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
    
    public void setTipoFeedback(TiposFeedback solicitacao){
        this.solicitacao = solicitacao;
    }
    
}