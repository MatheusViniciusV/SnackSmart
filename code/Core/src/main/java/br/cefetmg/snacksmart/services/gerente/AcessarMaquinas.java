package br.cefetmg.snacksmart.services.gerente;
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
import java.io.InputStream;
import br.cefetmg.snacksmart.exceptions.dao.FormatoArquivoInvalidoException;


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
    
    public MaquinaDTO getMaquinaPorCodigo(int codigo) throws PersistenciaException {
        return maquinaDAO.acessarMaquina(codigo);
    }

    public MaquinaDTO getMaquinaPorCodigoStatus(int codigo, StatusMaquina status) throws PersistenciaException {
        return maquinaDAO.acessarMaquina(codigo, status);
    }

    public MaquinaDTO getMaquinaPorTipoStatus(TipoMaquina tipo, StatusMaquina status) throws PersistenciaException {
        return maquinaDAO.acessarMaquinaTipoStatus(tipo, status);
    }

    private int gerarCodigo() throws PersistenciaException{
        int codigo = maquinaDAO.acessarTodasMaquinasSemExcecoes().size() + 1;
        return codigo;
    }
    
    public void formAddMaquina(String nome, String tipoStr, String locatarioCPF, String localizacao, InputStream imagemBytes) throws PersistenciaException{
        int codigo = gerarCodigo();
        StatusMaquina status = StatusMaquina.DISPONIVEL;
        TipoMaquina tipo = TipoMaquina.fromString(tipoStr);
        LocatarioDTO locatario; 
        LocatarioDAO locatarioDAO = new LocatarioDAO();
        locatario = locatarioDAO.consultarPorCPF(locatarioCPF);
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

    public void formAtualizarMaquina(int codigo, String novoNome, String novaLocalizacao, String novoLocatarioCPF, String statusStr, InputStream novaImagemBytes) throws PersistenciaException{              
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
        if (novaImagemBytes != null){    
            maquinaDTO.setImagem(novaImagemBytes);
        } 
        else 
            maquinaDTO.setImagem(maquinaDTO.getImagem());
        maquinaDAO.atualizarMaquina(maquinaDTO);
    } 
}
 
    
  
    

