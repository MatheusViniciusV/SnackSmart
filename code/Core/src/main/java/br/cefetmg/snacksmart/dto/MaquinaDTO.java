package br.cefetmg.snacksmart.dto;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoMaquina;
/* @author Arthur Milagres */
public class MaquinaDTO {
    private String nome, localizacao;
    private LocatarioDTO locatarioResponsavel;
    private final int codigo;
    private final TipoMaquina tipo;
    private int valor;
    private byte[] imagem; 
    private StatusMaquina status;

    public MaquinaDTO(String nome, int codigo, byte[] imagem, TipoMaquina tipo, int valor, String localizacao, LocatarioDTO locatario, StatusMaquina status){        
        this.nome = nome;
        this.codigo = codigo;
        this.imagem = imagem;
        this.localizacao = localizacao;
        this.tipo = tipo;
        this.valor = valor;
        this.status = status;
        this.locatarioResponsavel = locatario; 
    }
    public MaquinaDTO(){        
        this.nome = null;
        this.codigo = 0;
        this.imagem = null;
        this.localizacao = null;
        this.tipo = null; 
        this.valor = 0;
        this.status = null;
        this.locatarioResponsavel = null; 
    }
    
     
    public String getNome() {
        return nome;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public byte[] getImagem() {
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
    public int getValor() {
        return valor;
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
    
    public void setLocatarioResponsavel(LocatarioDTO novoLocatarioResponsavel) {
        locatarioResponsavel = novoLocatarioResponsavel;
    }
    
    public void setStatus(StatusMaquina novoStatus) {
        status = novoStatus;   
    }
    public void setValor(int  novoValor) {
        valor = novoValor;   
    }
}
