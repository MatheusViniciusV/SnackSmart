package br.cefetmg.snacksmart.service_gerente;
import br.cefetmg.snacksmart.dao.MaquinaDAO;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.dao.LocatarioDAO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoMaquina;
import java.util.ArrayList;
//Exceptions
import br.cefetmg.snacksmart.exceptions.service_maquinas.FormatoArquivoInvalidoException;


public class AcessarMaquinas {    
    private final IMaquinaDAO maquinaDAO;
    public AcessarMaquinas() {
        maquinaDAO = new MaquinaDAO();
    }
    
    public ArrayList<MaquinaDTO> getAllMaquinasGerente() throws PersistenciaException{
        ArrayList maquinas = maquinaDAO.acessarTodasMaquinas();
        return maquinas;
    }
    
    public ArrayList<MaquinaDTO> getAllMaquinasLocatario(int locatarioId) throws PersistenciaException{
        ArrayList maquinas = maquinaDAO.acessarTodasMaquinas(locatarioId);
        return maquinas;
    }
    
    private int gerarCodigo() throws PersistenciaException{
        int codigo = maquinaDAO.acessarTodasMaquinasSemExcecoes().size() + 1;
        return codigo;
    }
    
    public void formAddMaquina(String nome, String tipoStr, String locatarioCPF, String localizacao, byte[] imagemBytes) throws PersistenciaException{
        int codigo = gerarCodigo();
        StatusMaquina status = StatusMaquina.DISPONIVEL;
        TipoMaquina tipo = TipoMaquina.fromString(tipoStr);
        LocatarioDTO locatario; 
        LocatarioDAO locatarioDAO = new LocatarioDAO();
        if ("nenhum".equals(locatarioCPF))
            locatario = null; 
        else {    
            locatario = locatarioDAO.consultarPorCPF(locatarioCPF);
        }
        try {
            MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, imagemBytes, tipo, localizacao, locatario, status); 
            maquinaDAO.adicionarMaquina(maquinaDTO);
        } catch (FormatoArquivoInvalidoException e){
            MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, null, tipo, localizacao, locatario, status); 
            maquinaDAO.adicionarMaquina(maquinaDTO);
        }
    }

    public void remocaoMaquina(int codigo) throws PersistenciaException{
        maquinaDAO.removerMaquina(codigo);
    }

    public void formAtualizarMaquina(int codigo, String novoNome, String novaLocalizacao, String novoLocatarioCPF, String statusStr, byte[]novaImagemBytes) throws PersistenciaException{              
        MaquinaDTO maquinaDTO = maquinaDAO.acessarMaquina(codigo); 
        if (!"".equals(novoNome))
            maquinaDTO.setNome(novoNome);
        if (!"".equals(novaLocalizacao))
            maquinaDTO.setLocalizacao(novaLocalizacao);             
        if (!"".equals(novoLocatarioCPF)){
            LocatarioDAO locatarioDAO = new LocatarioDAO();
            LocatarioDTO novoLocatario = locatarioDAO.consultarPorCPF(novoLocatarioCPF);
            maquinaDTO.setLocatarioResponsavel(novoLocatario);
        }
        StatusMaquina status = StatusMaquina.fromString(statusStr);
        maquinaDTO.setStatus(status);

        try {
            maquinaDTO.setImagem(novaImagemBytes);
        } catch (FormatoArquivoInvalidoException e) {
            e.printStackTrace(); 
        }
        maquinaDAO.atualizarMaquina(maquinaDTO);
    } 
}
 
    
  
    

