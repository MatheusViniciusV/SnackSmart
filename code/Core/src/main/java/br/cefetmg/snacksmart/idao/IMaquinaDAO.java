package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.util.ArrayList;
/**
 *
 * @author Arthur Milagres
 */
public interface IMaquinaDAO {
    MaquinaDTO acessarMaquina(int codigo) throws PersistenciaException;
    ArrayList<MaquinaDTO> acessarTodasMaquinas() throws PersistenciaException;
    ArrayList<MaquinaDTO> acessarTodasMaquinas(int locatarioId) throws PersistenciaException;;
    void adicionarMaquina(MaquinaDTO novaMaquina) throws PersistenciaException;
    void atualizarMaquina(MaquinaDTO updatedMaquina) throws PersistenciaException;
    void removerMaquina(int codigo) throws PersistenciaException;
}
