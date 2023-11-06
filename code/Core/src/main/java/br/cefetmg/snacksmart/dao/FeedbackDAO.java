/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.conection.EstabelecerConexao;
import br.cefetmg.snacksmart.dto.FeedbackDTO;
import br.cefetmg.snacksmart.idao.IFeedbackDAO;
import br.cefetmg.snacksmart.utils.enums.TiposFeedback;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author marco
 */
public class FeedbackDAO implements IFeedbackDAO {
    private Connection conexao = null;  
    @Override
    public FeedbackDTO get(int codigo) {
        String sql = "SELECT autor,  recepitor, conteudo, tipo" +
                     "FROM feedbacks WHERE codigo = ?";
        conexao = EstabelecerConexao.obterConexao(); 
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String autor = resultSet.getString("autor");
                String recepitor = resultSet.getString("recipitor");
                String conteudo = resultSet.getString("conteudo");
                TiposFeedback tipo = TiposFeedback.valueOf(resultSet.getString("tipo"));
                FeedbackDTO feedback = new FeedbackDTO( codigo, autor, recepitor, conteudo, tipo);
                EstabelecerConexao.fecharConexao(conexao);
                return feedback;
            }
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
        }
        EstabelecerConexao.fecharConexao(conexao);
        return null;
    }

    @Override
    public ArrayList<FeedbackDTO> getAll() {
        String sql = "SELECT codigo, autor, recepitor, tipo" +
                     "FROM feedbacks";
        conexao = EstabelecerConexao.obterConexao(); 
        ArrayList<FeedbackDTO> feedbackVetor = new ArrayList<>();

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String autor = resultSet.getString("autor");
                String recepitor = resultSet.getString("recepitor");
                String conteudo = resultSet.getString("conteudo");
                TiposFeedback tipo = TiposFeedback.valueOf(resultSet.getString("tipo"));
                FeedbackDTO feedback = new FeedbackDTO( codigo, autor, recepitor, conteudo, tipo);
                feedbackVetor.add(feedback);
            }
            EstabelecerConexao.fecharConexao(conexao);       
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
            EstabelecerConexao.fecharConexao(conexao); 
        }
        EstabelecerConexao.fecharConexao(conexao);
        return feedbackVetor;
    }

    @Override
    public void set(FeedbackDTO feedback) {
        String sql = "INSERT INTO fedabacks ( codigo, autor, recepitor, conteudo,tipo)" +
                     "VALUES (?, ?, ?, ?, ?)";
        conexao = EstabelecerConexao.obterConexao(); 
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, feedback.getCodigo());
            preparedStatement.setString(2, feedback.getAutor() );
            preparedStatement.setString(3, feedback.getRecepitor() );
            preparedStatement.setString(4, feedback.getConteudo() );
            preparedStatement.setString(5, feedback.getTipo().name());


            preparedStatement.executeUpdate();
            EstabelecerConexao.fecharConexao(conexao);
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
            EstabelecerConexao.fecharConexao(conexao);
        }
    }
    
}
