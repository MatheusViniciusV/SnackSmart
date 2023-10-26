package br.cefetmg.snacksmart.dto;

import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import br.cefetmg.snacksmart.dao.MaquinaDAO;

// Importe das exceções usadas
import br.cefetmg.snacksmart.exceptions.dto.NomeNuloException;
import br.cefetmg.snacksmart.exceptions.dto.NomeInvalidoTamanhoException;
import br.cefetmg.snacksmart.exceptions.dto.FilaAteracoesVaziaException;
/**
 *
 * @author Arthur Milagres
 */
public class MaquinaDTO {
    private String nome, codigo, imagem, localizacao, tipo, locatarioResponsavel, status;
    private String novoNome, novaImagem, novaLocalizacao, alterarStatus;
    
    MaquinaDTO(String nome, String codigo, String imagem, String tipo){
        this.nome = nome;
        this.codigo = codigo;
        this.imagem = imagem;
        this.tipo = tipo; 
        this.localizacao = "";
        this.status = "";
    }
    
    MaquinaDTO(String nome, String codigo, String imagem, String tipo, String localizacao){
        this.nome = nome;
        this.codigo = codigo;
        this.imagem = imagem;
        this.tipo = tipo; 
        this.localizacao = localizacao;
        this.status = "";
    }
    
    public void SetNomeMaquina(String nome) throws NomeNuloException, NomeInvalidoTamanhoException {
        if(nome.length() == 0) 
            throw new NomeNuloException("A Máquina precisa ter um nome.");
        else if(nome.length() > 30 || nome.length() < 6) 
            throw new NomeInvalidoTamanhoException("O nome precisa ter de 6 a 30 caracteres");
        
        novoNome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    
    
    public void SetLocalizacao(String pais, String cidade, String rua, String info) throws NomeNuloException {
        if(pais.length() == 0 || cidade.length() == 0 ||  rua.length() == 0) 
            throw new NomeNuloException("É necessário preencher todos os campos");
     
        novaLocalizacao = rua + ", " + cidade + " - " + pais + " (" + info + ")";
    }
    
    public String getLocalizacao() {
        return localizacao;
    }
    
    
    public void AlterarStatus(String status){
        alterarStatus = status;
    }
    
    public void atualizarInformacoes() throws FilaAteracoesVaziaException {
        String novoNome = (this.novoNome.length() == 0) ? this.nome : this.novoNome;
        String novaLocalizacao = (this.novaLocalizacao.length() == 0) ? this.localizacao : this.novaLocalizacao;
        
        
        if(this.novoNome.length() != 0 || this.novaLocalizacao.length() != 0) {
            MaquinaDTO novaMaquina = new MaquinaDTO(novoNome, this.codigo, this.imagem, this.tipo, novaLocalizacao);
            IMaquinaDAO dao = new MaquinaDAO();
            
            
            dao.set(novaMaquina);
            // imcompleto, irei aguardar o banco de dados para colocar exceptions entre outras coisas.
            
            nome = novoNome;
            localizacao = novaLocalizacao;
        } else {
            throw new FilaAteracoesVaziaException("Nenhuma alteração foi especificada. Pelo menos um dos atributos deve ser modificado para aplicar alterações.");
        }
    }
}
