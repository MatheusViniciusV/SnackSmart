
package br.cefetmg.snacksmart.idao;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import java.util.ArrayList;
/**
 *
 * @author marco
 */
public interface IMaquinaDAO {

    /**
     *
     * @param maquina
     */
    void set(MaquinaDTO maquina);
    public MaquinaDTO get(int codigo);
    ArrayList<MaquinaDTO> listarTodos();
    
}
