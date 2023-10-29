package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import java.util.ArrayList;

/* @author Arthur Milagres  */

public class MaquinaDAO implements IMaquinaDAO {   
    @Override
    public MaquinaDTO get(String codigo) {
        return null;
    }    
    @Override
    public ArrayList<MaquinaDTO> getAll() {
        return null;
    }  
    
    @Override
    public void set(MaquinaDTO maquina) { 
    }
    
    @Override
    public void update(String codigo, MaquinaDTO updatedMaquina) {  
    
    }
    
    @Override
    public void remove(String codigo) { 
    }
}

