
package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.IAluguelDAO;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoMaquina;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class AluguelDAO implements IAluguelDAO{

    @Override
    public Double getAluguel(int codigo) throws PersistenciaException {          
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM `maquina` WHERE pk = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Double aluguel = resultSet.getDouble("aluguel");
               
                int locatarioId = resultSet.getInt("locatario__fk");               
                LocatarioDAO locatarioDAO = new LocatarioDAO();
                LocatarioDTO locatario = locatarioDAO.consultarPorId(locatarioId);
                
                resultSet.close();
                preparedStatement.close();
                conexao.close();
                
                return aluguel;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }       
        return null;
    }

    @Override
    public ArrayList<Double> getAlugueis(int locatarioId) throws PersistenciaException {
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM maquina WHERE locatario__fk = ? AND status != 'REMOVIDA'";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, locatarioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Double> aluguelVetor = null; 
            if (resultSet.next()) {
                aluguelVetor = new ArrayList<>();
                do {
                    Double aluguel = resultSet.getDouble("aluguel");

                    aluguelVetor.add(aluguel);
                } while (resultSet.next());
            }           
            resultSet.close();
            preparedStatement.close();
            conexao.close();    

            return  aluguelVetor; 

        }  catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
  
}
