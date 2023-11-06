package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.conection.EstabelecerConexao;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import java.util.ArrayList;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoMaquina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* @author Arthur Milagres  */

public class MaquinaDAO implements IMaquinaDAO {    
    private Connection conexao = null;  
    @Override
    public MaquinaDTO get(int codigo) {
        String sql = "SELECT nome, imagem, tipo, localizacao, locatario, status " +
                     "FROM maquinas WHERE codigo = ?";
        conexao = EstabelecerConexao.obterConexao(); 
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                byte[] imagem = resultSet.getBytes("imagem");
                TipoMaquina tipo = TipoMaquina.valueOf(resultSet.getString("tipo"));
                int valor = 0;
                String localizacao = resultSet.getString("localizacao");
                String locatarioStr = resultSet.getString("locatario");
                LocatarioDTO locatario = new LocatarioDTO(locatarioStr);
                StatusMaquina status = StatusMaquina.valueOf(resultSet.getString("status"));
                MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, imagem, tipo, valor, localizacao, locatario, status);
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
                int codigo = resultSet.getInt("codigo");
                String nome = resultSet.getString("nome");
                byte[] imagem = resultSet.getBytes("imagem");
                TipoMaquina tipo = TipoMaquina.valueOf(resultSet.getString("tipo"));
                int valor = 0;
                String localizacao = resultSet.getString("localizacao");
                String locatarioStr = resultSet.getString("locatario");
                LocatarioDTO locatario = new LocatarioDTO(locatarioStr);
                StatusMaquina status = StatusMaquina.valueOf(resultSet.getString("status"));

                MaquinaDTO maquina = new MaquinaDTO(nome, codigo, imagem, tipo, valor, localizacao, locatario, status);
                maquinasVetor.add(maquina);
            }
            EstabelecerConexao.fecharConexao(conexao);       
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
            EstabelecerConexao.fecharConexao(conexao); 
        }
        EstabelecerConexao.fecharConexao(conexao);
        return maquinasVetor;
    }  
    
    @Override
    public void set(MaquinaDTO maquina) {
        String sql = "INSERT INTO maquinas (nome, codigo, imagem, tipo, localizacao, locatario, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        conexao = EstabelecerConexao.obterConexao(); 
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, maquina.getNome());
            preparedStatement.setInt(2, maquina.getCodigo());
            preparedStatement.setBytes(3, maquina.getImagem());
            preparedStatement.setString(4, maquina.getTipo().name());
            preparedStatement.setString(5, maquina.getLocalizacao());
            preparedStatement.setString(6, maquina.getLocatario().getNome());
            preparedStatement.setString(7, maquina.getStatus().name());

            preparedStatement.executeUpdate();
            EstabelecerConexao.fecharConexao(conexao);
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
            EstabelecerConexao.fecharConexao(conexao);
        }
    }
    
    @Override
    public void update(MaquinaDTO updatedMaquina) {
        String sql = "UPDATE maquinas SET nome = ?, imagem = ?, tipo = ?, " +
                     "localizacao = ?, locatario = ?, status = ? WHERE codigo = ?";
        conexao = EstabelecerConexao.obterConexao();
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedMaquina.getNome());
            preparedStatement.setBytes(2, updatedMaquina.getImagem());
            preparedStatement.setString(3, updatedMaquina.getTipo().name());
            preparedStatement.setString(4, updatedMaquina.getLocalizacao());
            preparedStatement.setString(5, updatedMaquina.getLocatario().getNome());
            preparedStatement.setString(6, updatedMaquina.getStatus().name());
            preparedStatement.setInt(7, updatedMaquina.getCodigo());

            preparedStatement.executeUpdate();
            EstabelecerConexao.fecharConexao(conexao);
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
            EstabelecerConexao.fecharConexao(conexao);
        }
    }
    
    @Override
    public void remove(int codigo) {
        String sql = "DELETE FROM maquinas WHERE codigo = ?";
        conexao = EstabelecerConexao.obterConexao();
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
            EstabelecerConexao.fecharConexao(conexao);
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
            EstabelecerConexao.fecharConexao(conexao);
        }
        EstabelecerConexao.fecharConexao(conexao);
    }
    
}

