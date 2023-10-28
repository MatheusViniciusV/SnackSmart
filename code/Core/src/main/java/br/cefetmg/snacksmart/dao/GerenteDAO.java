package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.sql.SQLException;

/**
 *
 * @author eloym
 */
public class GerenteDAO implements IGerenteDAO {
    @Override
    public GerenteDTO get() throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
             
            String sql = "SELECT * FROM `gerente`";
             
            PreparedStatement pstmt = connection.prepareStatement(sql);
             
            ResultSet rs = pstmt.executeQuery();
             
            GerenteDTO gerente = null;
             
            // * Criar um gerente com o resultado da busca.
             
            rs.close();
            pstmt.close();
            connection.close();
            
            return gerente;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public void set(GerenteDTO gerente) throws PersistenciaException {
        
    }
}
