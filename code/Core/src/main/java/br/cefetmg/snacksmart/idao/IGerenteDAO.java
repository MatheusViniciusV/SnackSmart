package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;

/**
 *
 * @author eloym
 */
public interface IGerenteDAO {
    GerenteDTO get()  throws PersistenciaException;
    
    int set(GerenteDTO gerente) throws PersistenciaException;
}
