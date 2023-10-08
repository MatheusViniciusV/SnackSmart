
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
    public MaquinaDTO get();
    void set(MaquinaDTO maquina);
    ArrayList<MaquinaDTO> listarTodos();
    
}
