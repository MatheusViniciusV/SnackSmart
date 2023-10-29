package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.conection.EstabelecerConexao;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import java.util.ArrayList;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* @author Arthur Milagres  */

public class MaquinaDAO implements IMaquinaDAO {    
    private Connection conexao = null;  
    @Override
    public MaquinaDTO get(String codigo) {
        String sql = "SELECT nome, imagem, tipo, localizacao, locatario, status " +
                     "FROM maquinas WHERE codigo = ?";
        conexao = EstabelecerConexao.obterConexao(); 
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                byte[] imagem = resultSet.getBytes("imagem");
                String tipo = resultSet.getString("tipo");
                String localizacao = resultSet.getString("localizacao");
                String locatario = resultSet.getString("locatario");
                StatusMaquina status = StatusMaquina.valueOf(resultSet.getString("status"));
                MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, imagem, tipo, localizacao, locatario, status);
                EstabelecerConexao.fecharConexao(conexao);
                return maquinaDTO;
            }
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
        }
        EstabelecerConexao.fecharConexao(conexao);
        return null; //se não encontrar retorna null
    }    
    
    @Override
    public ArrayList<MaquinaDTO> getAll() {
        String sql = "SELECT codigo, nome, imagem, tipo, localizacao, locatario, status " +
                     "FROM maquinas";
        conexao = EstabelecerConexao.obterConexao(); 
        ArrayList<MaquinaDTO> maquinasVetor = new ArrayList<>();

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String codigo = resultSet.getString("codigo");
                String nome = resultSet.getString("nome");
                byte[] imagem = resultSet.getBytes("imagem");
                String tipo = resultSet.getString("tipo");
                String localizacao = resultSet.getString("localizacao");
                String locatario = resultSet.getString("locatario");
                StatusMaquina status = StatusMaquina.valueOf(resultSet.getString("status"));

                MaquinaDTO maquina = new MaquinaDTO(nome, codigo, imagem, tipo, localizacao, locatario, status);
                maquinasVetor.add(maquina);
            }
            EstabelecerConexao.fecharConexao(conexao);       
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
            EstabelecerConexao.fecharConexao(conexao); 
        }
        
        return maquinasVetor;
    }  
    
    @Override
    public void set(MaquinaDTO maquina) {
        String sql = "INSERT INTO maquinas (nome, codigo, imagem, tipo, localizacao, locatario, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        conexao = EstabelecerConexao.obterConexao(); 
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, maquina.getNome());
            preparedStatement.setString(2, maquina.getCodigo());
            preparedStatement.setBytes(3, maquina.getImagem());
            preparedStatement.setString(4, maquina.getTipo());
            preparedStatement.setString(5, maquina.getLocalizacao());
            preparedStatement.setString(6, maquina.getLocatario());
            preparedStatement.setString(7, maquina.getStatus().name());

            preparedStatement.executeUpdate();
            EstabelecerConexao.fecharConexao(conexao);
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
            EstabelecerConexao.fecharConexao(conexao);
        }
    }
    
    @Override
    public void update(String codigo, MaquinaDTO updatedMaquina) {
        String sql = "UPDATE maquinas SET nome = ?, imagem = ?, tipo = ?, " +
                     "localizacao = ?, locatario = ?, status = ? WHERE codigo = ?";
        conexao = EstabelecerConexao.obterConexao();
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedMaquina.getNome());
            preparedStatement.setBytes(2, updatedMaquina.getImagem());
            preparedStatement.setString(3, updatedMaquina.getTipo());
            preparedStatement.setString(4, updatedMaquina.getLocalizacao());
            preparedStatement.setString(5, updatedMaquina.getLocatario());
            preparedStatement.setString(6, updatedMaquina.getStatus().name());
            preparedStatement.setString(7, codigo);

            preparedStatement.executeUpdate();
            EstabelecerConexao.fecharConexao(conexao);
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
            EstabelecerConexao.fecharConexao(conexao);
        }
    }
    
    @Override
    public void remove(String codigo) {
        String sql = "UPDATE maquinas SET nome = NULL, imagem = NULL, tipo = NULL, " +
                     "localizacao = NULL, locatario = NULL, status = 'REMOVIDO' WHERE codigo = ?";
        conexao = EstabelecerConexao.obterConexao();
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, codigo);
            preparedStatement.executeUpdate();
            EstabelecerConexao.fecharConexao(conexao);
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
            EstabelecerConexao.fecharConexao(conexao);
        }
    }
    
}

