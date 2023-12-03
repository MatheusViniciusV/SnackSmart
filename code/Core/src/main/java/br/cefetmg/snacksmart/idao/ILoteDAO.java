package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.LoteDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.util.ArrayList;

public interface ILoteDAO {
    
    int inserir(LoteDTO loteDTO) throws PersistenciaException;

    boolean atualizar(LoteDTO loteDTO) throws PersistenciaException;

    boolean delete(int id) throws PersistenciaException;

    ArrayList<LoteDTO> listarTodos() throws PersistenciaException;

    LoteDTO consultarPorId(int id) throws PersistenciaException;
    
    ArrayList<LoteDTO> listarPorLocatario(int id) throws PersistenciaException;
}
