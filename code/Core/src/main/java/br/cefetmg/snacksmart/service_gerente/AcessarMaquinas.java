package br.cefetmg.snacksmart.service_gerente;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import br.cefetmg.snacksmart.dao.MaquinaDAO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import java.util.ArrayList;
//Exceptions
import br.cefetmg.snacksmart.exceptions.service_maquinas.FormatoArquivoInvalidoException;
import java.sql.SQLException;




public class AcessarMaquinas {    
    private IMaquinaDAO maquinaDAO;
    public AcessarMaquinas() {
        maquinaDAO = new MaquinaDAO();
    }
    
    public ArrayList<MaquinaDTO> getAllMaquinas() throws SQLException{
        return maquinaDAO.getAll();
    }
    
    private String gerarCodigo(){
        int num = maquinaDAO.getAll().size() + 1;
        String codigo = String.format("%04d", num); 
        return codigo; 
    }
    
    public void formAddMaquina(String nome, String tipo, String locatario, String localizacao, byte[] imagemBytes){
        String codigo = gerarCodigo();
        StatusMaquina status = StatusMaquina.DISPONIVEL;
        try {
            MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, imagemBytes, tipo, localizacao, locatario, status); 
            maquinaDAO.set(maquinaDTO);
            throw new FormatoArquivoInvalidoException("O formato do arquivo é inválido.");
        } catch (FormatoArquivoInvalidoException e){
            MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, null, tipo, localizacao, locatario, status); 
            maquinaDAO.set(maquinaDTO);
        }
    }

    public void remocaoMaquina(String codigo){
        maquinaDAO.remove(codigo);
    }

    public void formAtualizarMaquina(String codigo, String novoNome, String novaLocalizacao, String novoLocatario, String statusStr, byte[]novaImagemBytes){              
        MaquinaDTO maquinaDTO = maquinaDAO.get(codigo); 
        if (novoNome != null)
            maquinaDTO.setNome(novoNome);
        if (novaLocalizacao != null)
            maquinaDTO.setLocalizacao(novaLocalizacao);             
        if (novoLocatario != null)
            maquinaDTO.setLocatarioResponsavel(novoLocatario);

        StatusMaquina status = StatusMaquina.fromString(statusStr);
        maquinaDTO.setStatus(status);

        try {
            maquinaDTO.setImagem(novaImagemBytes);
            maquinaDAO.update(codigo, maquinaDTO);
            throw new FormatoArquivoInvalidoException("O formato do arquivo é inválido.");
        } catch (FormatoArquivoInvalidoException e){
            maquinaDAO.update(codigo, maquinaDTO);
        }          
    } 
}
 
    
  
    

