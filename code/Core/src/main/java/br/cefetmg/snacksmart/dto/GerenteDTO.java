package br.cefetmg.snacksmart.dto;

import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.dao.GerenteDAO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.util.Random;

// Importe das exceções usadas
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import br.cefetmg.snacksmart.exceptions.dto.SenhaInvalidaTamanhoException;
import br.cefetmg.snacksmart.exceptions.dto.NomeNuloException;
import br.cefetmg.snacksmart.exceptions.dto.CPFInvalidoException;
import br.cefetmg.snacksmart.exceptions.dto.FilaAteracoesVaziaException;

/**
 *
 * @author eloym
 */
public class GerenteDTO {
    private String nome,
           cpf,
           token,
           novoNome,
           novoCPF,
           novoToken;
    
    GerenteDTO(String nome, String cpf, String token) {
        this.nome = nome;
        this.cpf = cpf;
        this.token = token;
        
        this.novoCPF = this.novoNome = this.novoToken = "";
    }
        
    public void setCPF(String cpf) throws CPFInvalidoException{
        if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"))
            throw new CPFInvalidoException("CPF do gerente invalido.");
            
        novoCPF = cpf;
    }
    
    public String getCPF() {
        return cpf;
    }
    
    public void SetNome(String nome) throws NomeNuloException {
        if(nome.length() == 0) 
            throw new NomeNuloException("Nome do Gerente não deve ser nulo.");
        
        novoNome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
//  Define com um token aleatório e retorna esse novo tonken;
    public String definirToken()  throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        
        Random random = new Random();

        StringBuilder tokenAleatorio = new StringBuilder();
        
        for (int i = 0; i < 8; i++) {
            int indice = random.nextInt(caracteres.length());
            char caracterAleatorio = caracteres.charAt(indice);
            tokenAleatorio.append(caracterAleatorio);
        }
        
        String token = tokenAleatorio.toString();
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte hash[] = algorithm.digest(token.getBytes("UTF-8"));
        
        StringBuilder aux = new StringBuilder();
        for (byte b : hash) {
            aux.append(String.format("%02X", 0xFF & b));
        }
        
        novoToken = aux.toString();
        
        return novoToken;
    }
    
//  Define com um token passado e retorna esse tonken
    public void definirToken(String token) throws NoSuchAlgorithmException, UnsupportedEncodingException, SenhaInvalidaTamanhoException {
        if(token.length() < 8)
            throw new SenhaInvalidaTamanhoException("Senha deve ter mais de 8 caracteres.");
        
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte hash[] = algorithm.digest(token.getBytes("UTF-8"));
        
        StringBuilder aux = new StringBuilder();
        for (byte b : hash) {
            aux.append(String.format("%02X", 0xFF & b));
        }
        
        novoToken = aux.toString();
    }
    
    public String getToken() {
        return token;
    }
    
    public void aplicarMudanças() throws FilaAteracoesVaziaException, PersistenciaException {
        String novoNome = (this.novoNome.length() == 0) ? this.nome : this.novoNome;
        String novoCPF = (this.novoCPF.length() == 0) ? this.cpf : this.novoCPF;
        String novoToken  = (this.novoToken.length() == 0) ? this.token : this.novoToken;
        
        
        if(this.novoNome.length() != 0 || this.novoCPF.length() != 0 || this.novoToken.length() != 0) {
            GerenteDTO novoGerente = new GerenteDTO(novoNome, novoCPF, novoToken);
            IGerenteDAO dao = new GerenteDAO();
            
            
            dao.set(novoGerente);
            // imcompleto, irei aguardar o banco de dados para colocar exceptions entre outras coisas.
            
            cpf = novoCPF;
            nome = novoNome;
            token = novoToken;
        } else {
            throw new FilaAteracoesVaziaException("Nenhuma alteração foi especificada. Pelo menos um dos atributos (nome, CPF ou token) deve ser modificado para aplicar alterações.");
        }
    }
}
