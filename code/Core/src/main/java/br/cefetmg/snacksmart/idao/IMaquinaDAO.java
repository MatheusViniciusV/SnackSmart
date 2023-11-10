package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoMaquina;

import java.util.ArrayList;
/**
 *
 * @author Arthur Milagres
 */
public interface IMaquinaDAO {
    MaquinaDTO acessarMaquina(int codigo) throws PersistenciaException;
    MaquinaDTO acessarMaquina(int codigo, StatusMaquina status) throws PersistenciaException;
    MaquinaDTO acessarMaquinaTipoStatus(TipoMaquina tipo, StatusMaquina status) throws PersistenciaException;
    ArrayList<MaquinaDTO> acessarTodasMaquinas() throws PersistenciaException;;
    void adicionarMaquina(MaquinaDTO novaMaquina) throws PersistenciaException;;
    void atualizarMaquina(MaquinaDTO updatedMaquina) throws PersistenciaException;;
    void removerMaquina(int codigo) throws PersistenciaException;;
}
