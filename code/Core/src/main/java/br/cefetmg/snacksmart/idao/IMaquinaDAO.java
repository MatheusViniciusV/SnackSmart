package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.MaquinaDTO;
import java.util.ArrayList;
/**
 *
 * @author Arthur Milagres
 */
public interface IMaquinaDAO {
    MaquinaDTO get(int codigo);
    ArrayList<MaquinaDTO> getAll();
    void set(MaquinaDTO novaMaquina);
    void update(MaquinaDTO updatedMaquina);
    void remove(int codigo);
}
