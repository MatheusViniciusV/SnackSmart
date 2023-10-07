package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.GerenteDTO;

/**
 *
 * @author eloym
 */
public interface IGerenteDAO {
    GerenteDTO get();
    
    void set(GerenteDTO gerente);
}
