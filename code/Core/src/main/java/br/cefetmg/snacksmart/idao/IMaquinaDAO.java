package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.MaquinaDTO;
/**
 *
 * @author Arthur Milagres
 */
public interface IMaquinaDAO {
    MaquinaDTO get();
    
    void set(MaquinaDTO gerente);
}
