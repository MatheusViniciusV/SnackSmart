
package br.cefetmg.snacksmart.dto;

import br.cefetmg.snacksmart.dao.MaquinaDAO;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;

import br.cefetmg.snacksmart.exceptions.dto.FilaAteracoesVaziaException;
/**
 *
 * @author marco
 */
public class MaquinaDTO {
    private String localizacao;
    private Double  valor;
    private int codigo, estado;
    // para estado 0 = disponivel, 1 = alugada, 2 = em manutenção 

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public int getCodigo(){
        return codigo;
    }
    public void setLacalizacao(String localizacao){
         this.localizacao = localizacao;
    }
    public String getLocalizacao(){
        return localizacao;
    }
    
    public void setValor(Double valor){
         this.valor = valor;
    }
    public Double getValor(){
        return valor;
    }
    public void setEstado(int estado){
         this.estado = estado;
    }
    public int getEstado(){
        return estado;
    }
}