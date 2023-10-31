package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.util.ArrayList;

public interface ILocatarioDAO {

    Long inserir(LocatarioDTO locatarioDTO) throws PersistenciaException;

    boolean atualizar(LocatarioDTO locatarioDTO) throws PersistenciaException;

    boolean delete(LocatarioDTO locatarioDTO) throws PersistenciaException;

    ArrayList<LocatarioDTO> listarTodos() throws PersistenciaException;

    LocatarioDTO consultarPorPk(Long pk) throws PersistenciaException;
    
    LocatarioDTO consultarPorCPF(String cpf) throws PersistenciaException;
}
