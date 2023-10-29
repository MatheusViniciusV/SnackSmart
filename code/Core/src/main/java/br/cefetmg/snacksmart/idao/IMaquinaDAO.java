
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
     * @return
     */
    void set(MaquinaDTO maquina);
    public MaquinaDTO get();
    ArrayList<MaquinaDTO> listarTodos();
    
}
