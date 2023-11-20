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
    
    
    public void setData(String novaData){
        data = novaData;
    }
    public void setDescricao(String novaDescricao){
        descricao = novaDescricao;
    }
    
}
    