package br.cefetmg.snacksmart.services.locatario;

import br.cefetmg.snacksmart.dao.LoteDAO;
import br.cefetmg.snacksmart.dto.LoteDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.ILoteDAO;
import java.util.ArrayList;
/* @author Arthur Milagres */
public class AcessarLotes {
    
    private final ILoteDAO loteDAO;
    
    public AcessarLotes() {
        loteDAO = new LoteDAO();
    }
    
    public ArrayList<LoteDTO> recuperarLotesLocatario(String cpf) throws PersistenciaException{
        ArrayList lotes = loteDAO.listarPorLocatario(cpf);
        return lotes;
    }
    
    public ArrayList<LoteDTO> recuperarTodosLotes() throws PersistenciaException{
        ArrayList lotes = loteDAO.listarTodos();
        return lotes;
    }
    
    public LoteDTO recuperarLotePorId(int id) throws PersistenciaException{
        LoteDTO lote = loteDAO.consultarPorId(id);
        return lote;
    }
    
    public void removerLote(int id) throws PersistenciaException{
        loteDAO.delete(id);
    }
    
    public void atualizarLote(LoteDTO lote) throws PersistenciaException{        
        loteDAO.atualizar(lote);
    }
    
    public void adicionarNovoLote(LoteDTO loteDTO) throws PersistenciaException{
        loteDAO.inserir(loteDTO);
    }
}
