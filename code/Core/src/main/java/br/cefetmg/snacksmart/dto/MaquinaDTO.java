package br.cefetmg.snacksmart.dto;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoMaquina;
import com.google.gson.Gson;

import java.io.InputStream;
/* @author Arthur Milagres */
public class MaquinaDTO {
    private String nome, localizacao;
    private LocatarioDTO locatarioResponsavel;
    private final int codigo;
    private final TipoMaquina tipo;
    private InputStream imagem; 
    private String urlImagem;
    private StatusMaquina status;

    public MaquinaDTO(String nome, int codigo, InputStream imagem, TipoMaquina tipo, String localizacao, LocatarioDTO locatario, StatusMaquina status){        
        this.nome = nome;
        this.codigo = codigo;
        this.imagem = imagem;
        this.localizacao = localizacao;
        this.tipo = tipo; 
        this.status = status;
        this.locatarioResponsavel = locatario; 
    }
    public MaquinaDTO(){        
        this.nome = null;
        this.codigo = 0;
        this.imagem = null;
        this.localizacao = null;
        this.tipo = null; 
        this.status = null;
        this.locatarioResponsavel = null; 
    }
    
     
    public String getNome() {
        return nome;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public InputStream getImagem() {
        return imagem;
    }   
    
    public String getLocalizacao() {
        return localizacao;
    }
    
    public TipoMaquina getTipo() {
        return tipo;
    }
    
    public LocatarioDTO getLocatario() {
        return locatarioResponsavel;
    }
    
    public StatusMaquina getStatus() {
        return status;
    }
    public String getUrlImagem(){
        return urlImagem;
    }
    public void setNome(String novoNome) {
        nome = novoNome;
    }
    
    public void setImagem(InputStream novaImagem) {
        imagem = novaImagem;
    }
    
    public void setUrlImagem(String imagem) {
        urlImagem = imagem;
    }
    
    public void setLocalizacao(String novaLocalizacao) {
        localizacao = novaLocalizacao;
    }
    
    public void setLocatarioResponsavel(LocatarioDTO novoLocatarioResponsavel) {
        locatarioResponsavel = novoLocatarioResponsavel;
    }
    
    public void setStatus(StatusMaquina novoStatus) {
        status = novoStatus;   
    }

    public String toJson() {
        Gson gson = new Gson();

        return gson.toJson(this);
    }
}
