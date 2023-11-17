
package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.FeedbackDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.IFeedbackDAO;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import br.cefetmg.snacksmart.utils.enums.TiposFeedback;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class FeedbackDAO implements IFeedbackDAO {
    private Connection conexao = null;  
    @Override
    public FeedbackDTO get(int codigo) throws PersistenciaException {       
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM feedback WHERE codigo = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String mensagem = resultSet.getString("mensagem");
                TiposFeedback solicitacao = TiposFeedback.valueOf(resultSet.getString("tipoFeedback"));
                FeedbackDTO feedback = new FeedbackDTO(codigo, titulo, mensagem, solicitacao);
                
                resultSet.close();
                preparedStatement.close();
                conexao.close();
                
                return feedback;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return null;
    }
    
    @Override
    public ArrayList<FeedbackDTO> getAll() throws PersistenciaException{
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM feedback";          
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<FeedbackDTO> feedbackVetor = null;
            if (resultSet.next()) {
                feedbackVetor = new ArrayList<>();
                do {
                    int codigo = resultSet.getInt("codigo");
                    String titulo = resultSet.getString("titulo");
                    String mensagem = resultSet.getString("mensagem");
                    TiposFeedback solicitacao = TiposFeedback.valueOf(resultSet.getString("tipoFeedback"));
                    FeedbackDTO feedback = new FeedbackDTO(codigo, titulo, mensagem, solicitacao);
                    feedbackVetor.add(feedback);
                } while (resultSet.next());
            }       
            resultSet.close();
            preparedStatement.close();
            conexao.close();    
            
            return feedbackVetor;            
                  
        }  catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public void set(FeedbackDTO feedback) throws PersistenciaException{    
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "INSERT INTO feedback (codigo, titulo, mensagem, tipoFeedback)" +
                     "VALUES (?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                    
            preparedStatement.setInt(1, feedback.getCodigo());
            preparedStatement.setString(2, feedback.getTitulo() );
            preparedStatement.setString(3, feedback.getMensagem() );
            preparedStatement.setString(4, feedback.getTipoFeedback().toString());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conexao.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public void remove(String feedbackNome) throws PersistenciaException{    
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "DELETE FROM feedback WHERE titulo = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, feedbackNome);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conexao.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}