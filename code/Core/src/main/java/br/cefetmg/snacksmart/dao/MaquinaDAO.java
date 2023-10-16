package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import java.util.ArrayList;
/**
 *
 * @author marcos
 */
public class MaquinaDAO implements IMaquinaDAO {
    
    @Override
    public MaquinaDTO get(){
        //ainda sera implementrado
        return null;
   }


    @Override
    public void set(MaquinaDTO maquina){
        //ainda sera implementrado
    }

    @Override
    public ArrayList<MaquinaDTO> listarTodos() {
        ArrayList<MaquinaDTO> ArrayList = null ;
        return ArrayList;
    }
}
