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
    public void registraVistoria(VistoriaDTO vistoria) throws SQLException{             
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "INSERT INTO vistoria (descricao, data) " +
                     "VALUES (?, ?)";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                    
            preparedStatement.setString(1, vistoria.getData());
            preparedStatement.setString(2, vistoria.getDescricao());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conexao.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<VistoriaDTO> listarTodas() throws PersistenciaException{
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM vistorias";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                                 
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<VistoriaDTO> vistoriaVetor = null; 
            if (resultSet.next()) {
                vistoriaVetor = new ArrayList<>();
                do {
                    String descricao = resultSet.getString("descricao");
                    String data = resultSet.getString("data");
                    
                    VistoriaDTO novaVistoria = new VistoriaDTO(descricao, data);
                    vistoriaVetor.add(novaVistoria);
                } while (resultSet.next());
            }           
            resultSet.close();
            preparedStatement.close();
            conexao.close();    
            
            return vistoriaVetor; 
            
        }  catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
            
        }
        
    
}
