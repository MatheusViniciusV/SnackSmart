package br.cefetmg.snacksmart.dto;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;

/* @author Arthur Milagres */
public class MaquinaDTO {
    private String nome, localizacao, locatarioResponsavel;
    private final String codigo, tipo;
    private byte[] imagem; 
    StatusMaquina status = StatusMaquina.DISPONIVEL;
    public MaquinaDTO(String nome, String codigo, byte[] imagem, String tipo, String localizacao, String locatario, StatusMaquina status){        
        this.nome = nome;
        this.codigo = codigo;
        this.imagem = imagem;
        this.localizacao = localizacao;
        this.tipo = tipo; 
        this.status = status;
        this.locatarioResponsavel = locatario; 
    }
     
    public String getNome() {
        return nome;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public byte[] getImagem() {
        return imagem;
    }   
    
    public String getLocalizacao() {
        return localizacao;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public String getLocatario() {
        return locatarioResponsavel;
    }
    
    public StatusMaquina getStatus() {
        return status;
    }
      
    public void setNome(String novoNome) {
        nome = novoNome;
    }
    
    public void setImagem(byte[] novaImagem) {
        imagem = novaImagem;
    }
    
    public void setLocalizacao(String novaLocalizacao) {
        localizacao = novaLocalizacao;
    }
    
    public void setLocatarioResponsavel(String novoLocatarioResponsavel) {
        locatarioResponsavel = novoLocatarioResponsavel;
    }
    
    public void setStatus(StatusMaquina novoStatus) {
        status = novoStatus;   
    }
}
