package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.VistoriaDTO;
import br.cefetmg.snacksmart.idao.IVistoriaDAO;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import java.sql.Connection;
import java.sql.*;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.util.ArrayList;

/**
 *
 * @author VictorN77
 */
public class VistoriaDAO implements IVistoriaDAO{
    
        
    @Override
        public VistoriaDTO registraVistoria(VistoriaDTO vistoria) throws SQLException{
    
                ConnectionManager cnm = ConnectionManager.getInstance();
                try{
                
                    Connection cnx = cnm.getConnection();
                    
                    String SQL = "";
                    PreparedStatement ps = cnx.prepareStatement();
                }

    }
        @Override
        public ArrayList<VistoriaDTO> listarTodas() throws PersistenciaException{
        
            
        }
        
    
}
