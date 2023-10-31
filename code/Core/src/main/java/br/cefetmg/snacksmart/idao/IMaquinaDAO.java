package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.MaquinaDTO;
import java.util.ArrayList;
/**
 *
 * @author Arthur Milagres
 */
public interface IMaquinaDAO {
    MaquinaDTO get(String codigo);
    ArrayList<MaquinaDTO> getAll();
    void set(MaquinaDTO novaMaquina);
    void update(String codigo, MaquinaDTO updatedMaquina);
    void remove(String codigo);
}
