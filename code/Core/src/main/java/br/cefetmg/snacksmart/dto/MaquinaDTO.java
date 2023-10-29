
package br.cefetmg.snacksmart.dto;

import br.cefetmg.snacksmart.dao.MaquinaDAO;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;

import br.cefetmg.snacksmart.exceptions.dto.FilaAteracoesVaziaException;
/**
 *
 * @author marco
 */
public class MaquinaDTO {
    private String localizacao, novaLocalizacao;
    private Double  valor, novoValor;
    private int codigo, novoCodigo,estado,novoEstado;
    // para estado 0 = disponivel, 1 = alugada, 2 = em manutenção 

    public MaquinaDTO() {
        
    }
    
    public MaquinaDTO(int codigo, String localizacao, Double valor){
        this.codigo = codigo;
        this.localizacao = localizacao;
        this.valor = valor;
        estado = 0;
        
        this.novaLocalizacao = "";
        this.novoCodigo = 0;
        this.novoValor = 0.0;
        novoEstado = 0;
    }
    public void setCodigo(int codigo){
        novoCodigo = codigo;
    }
    public int getCodigo(){
        return codigo;
    }
    public void setLacalizacao(String localizacao){
        novaLocalizacao = localizacao;
    }
    public String getLocalizacao(){
        return localizacao;
    }
    
    public void setValor(Double valor){
        novoValor = valor;
    }
    public Double getValor(){
        return valor;
    }
    public void setEstado(int estado){
        novoEstado = estado;
    }
    public int getEstado(){
        return estado;
    }
    public void aplicarMudanças()throws FilaAteracoesVaziaException{
        int novoCodigo = (this.novoCodigo == 0) ? this.codigo : this.novoCodigo;
        String novaLocalizacao = (this.localizacao.length() == 0) ? this.localizacao : this.novaLocalizacao;
        Double novoValor  = (this.novoValor == 0.0) ? this.valor : this.novoValor;
        int novoEstado = (this.novoEstado == 0) ? this.estado : this.novoEstado;
        
        
        if(this.novoCodigo != 0 || this.novaLocalizacao.length() != 0 || this.novoValor != 0 || this.novoEstado != 0 ) {
            MaquinaDTO novaMaquina = new MaquinaDTO(novoCodigo, novaLocalizacao, novoValor);
            IMaquinaDAO dao = new MaquinaDAO();
            
            
            dao.set(novaMaquina);
            // imcompleto, irei aguardar o banco de dados para colocar exceptions entre outras coisas.
            
            codigo = novoCodigo;
            localizacao = novaLocalizacao;
            valor = novoValor;
            estado = novoEstado;
        } else {
            throw new FilaAteracoesVaziaException("Nenhuma alteração foi especificada. Pelo menos um dos atributos (Codigo, Localizacao, Valor ou Estado) deve ser modificado para aplicar alterações.");
        }
    }
}
