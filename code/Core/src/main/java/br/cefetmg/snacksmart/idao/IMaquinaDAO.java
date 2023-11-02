package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.MaquinaDTO;
import java.util.ArrayList;
/**
 *
 * @author Arthur Milagres
 */
public interface IMaquinaDAO {
    MaquinaDTO acessarMaquina(int codigo);
    ArrayList<MaquinaDTO> acessarTodasMaquinas();
    void adicionarMaquina(MaquinaDTO novaMaquina);
    void atualizarMaquina(MaquinaDTO updatedMaquina);
    void removerMaquina(int codigo);
}
