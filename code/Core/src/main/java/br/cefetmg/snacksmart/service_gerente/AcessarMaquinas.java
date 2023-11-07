package br.cefetmg.snacksmart.service_gerente;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import br.cefetmg.snacksmart.dao.MaquinaDAO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoMaquina;
import java.util.ArrayList;
//Exceptions
import br.cefetmg.snacksmart.exceptions.service_maquinas.FormatoArquivoInvalidoException;
import java.sql.SQLException;

public class AcessarMaquinas {    
    private final IMaquinaDAO maquinaDAO;
    public AcessarMaquinas() {
        maquinaDAO = new MaquinaDAO();
    }
    
    public ArrayList<MaquinaDTO> getAllMaquinas() throws SQLException{
        return maquinaDAO.getAll();
    }
    
    public MaquinaDTO getMaquinaPorCodigo(String codigo) {
        
    }
    
    private int gerarCodigo(){
        int codigo = maquinaDAO.getAll().size() + 1;
        return codigo;
    }
    
    public void formAddMaquina(String nome, String tipoStr, String locatarioStr, String localizacao, byte[] imagemBytes){
        int codigo = gerarCodigo();
        StatusMaquina status = StatusMaquina.DISPONIVEL;
        TipoMaquina tipo = TipoMaquina.fromString(tipoStr);
        LocatarioDTO locatario = new LocatarioDTO(locatarioStr);
        try {
            MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, imagemBytes, tipo, localizacao, locatario, status); 
            maquinaDAO.set(maquinaDTO);
            throw new FormatoArquivoInvalidoException("O formato do arquivo é inválido.");
        } catch (FormatoArquivoInvalidoException e){
            MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, null, tipo, localizacao, locatario, status); 
            maquinaDAO.set(maquinaDTO);
        }
    }

    public void remocaoMaquina(int codigo){
        maquinaDAO.remove(codigo);
    }

    public void formAtualizarMaquina(int codigo, String novoNome, String novaLocalizacao, String novoLocatarioStr, String statusStr, byte[]novaImagemBytes){              
        MaquinaDTO maquinaDTO = maquinaDAO.get(codigo); 
        if (novoNome != null)
            maquinaDTO.setNome(novoNome);
        if (novaLocalizacao != null)
            maquinaDTO.setLocalizacao(novaLocalizacao);             
        if (novoLocatarioStr != null){
            LocatarioDTO novoLocatario = new LocatarioDTO(novoLocatarioStr);
            maquinaDTO.setLocatarioResponsavel(novoLocatario);
        }
        StatusMaquina status = StatusMaquina.fromString(statusStr);
        maquinaDTO.setStatus(status);

        try {
            maquinaDTO.setImagem(novaImagemBytes);
            maquinaDAO.update(maquinaDTO);
            throw new FormatoArquivoInvalidoException("O formato do arquivo é inválido.");
        } catch (FormatoArquivoInvalidoException e){
            maquinaDAO.update(maquinaDTO);
        }          
    } 
}
 
    
  
    

