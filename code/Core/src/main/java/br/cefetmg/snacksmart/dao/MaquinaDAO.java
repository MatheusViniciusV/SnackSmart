package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import java.util.ArrayList;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoMaquina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;

/* @author Arthur Milagres  */

public class MaquinaDAO implements IMaquinaDAO {    
    @Override
    public MaquinaDTO acessarMaquina(int codigo) throws PersistenciaException{            
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT nome, imagem, tipo, localizacao, locatario__fk, status " +
                     "FROM maquina WHERE codigo = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                byte[] imagem = resultSet.getBytes("imagem");
                TipoMaquina tipo = TipoMaquina.valueOf(resultSet.getString("tipo"));
                String localizacao = resultSet.getString("localizacao");
                
                int locatarioId = resultSet.getInt("locatario");               
                LocatarioDAO locatarioDAO = new LocatarioDAO();
                LocatarioDTO locatario = locatarioDAO.consultarPorId(locatarioId);
                StatusMaquina status = StatusMaquina.valueOf(resultSet.getString("status"));
                MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, imagem, tipo, localizacao, locatario, status);
                
                resultSet.close();
                preparedStatement.close();
                conexao.close();
                
                return maquinaDTO;
            }
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
        } catch (ClassNotFoundException | PersistenciaException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null; 
    }    
    
    @Override
    public ArrayList<MaquinaDTO> acessarTodasMaquinas() throws PersistenciaException{
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM maquina WHERE status <> 'REMOVIDA'";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
 
            ArrayList<MaquinaDTO> maquinasVetor = null;                      
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                maquinasVetor = new ArrayList<>();
                do {
                    int codigo = resultSet.getInt("codigo");
                    String nome = resultSet.getString("nome");
                    byte[] imagem = resultSet.getBytes("imagem");
                    TipoMaquina tipo = TipoMaquina.valueOf(resultSet.getString("tipo"));
                    String localizacao = resultSet.getString("localizacao");

                    int locatarioId = resultSet.getInt("locatario");
                    LocatarioDAO locatarioDAO = new LocatarioDAO();
                    LocatarioDTO locatario = locatarioDAO.consultarPorId(locatarioId);

                    StatusMaquina status = StatusMaquina.valueOf(resultSet.getString("status"));

                    MaquinaDTO maquina = new MaquinaDTO(nome, codigo, imagem, tipo, localizacao, locatario, status);
                    maquinasVetor.add(maquina);
                } while (resultSet.next());
            }
            
            resultSet.close();
            preparedStatement.close();
            conexao.close();
            
            return maquinasVetor;           
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
        } catch (ClassNotFoundException | PersistenciaException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return null;  
    }  
    
    @Override
    public void adicionarMaquina(MaquinaDTO maquina) throws PersistenciaException {      
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "INSERT INTO maquina (nome, codigo, imagem, tipo, localizacao, locatario__fk, status, aluguel) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                    
            preparedStatement.setString(1, maquina.getNome());
            preparedStatement.setInt(2, maquina.getCodigo());
            preparedStatement.setBytes(3, maquina.getImagem());
            preparedStatement.setString(4, maquina.getTipo().name());
            preparedStatement.setString(5, maquina.getLocalizacao());            
            preparedStatement.setInt(6, maquina.getLocatario().getId());           
            preparedStatement.setString(7, maquina.getStatus().name());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            conexao.close();
            
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public void atualizarMaquina(MaquinaDTO updatedMaquina) throws PersistenciaException{        
        
        
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "UPDATE maquina SET nome = ?, imagem = ?, tipo = ?, " +
                     "localizacao = ?, locatario__fk = ?, status = ? WHERE codigo = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1, updatedMaquina.getNome());
            preparedStatement.setBytes(2, updatedMaquina.getImagem());
            preparedStatement.setString(3, updatedMaquina.getTipo().name());
            preparedStatement.setString(4, updatedMaquina.getLocalizacao());
            preparedStatement.setInt(5, updatedMaquina.getLocatario().getId());
            preparedStatement.setString(6, updatedMaquina.getStatus().name());
            preparedStatement.setInt(7, updatedMaquina.getCodigo());

            preparedStatement.executeUpdate();           
            preparedStatement.close();
            conexao.close();
            
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void removerMaquina(int codigo) throws PersistenciaException {    
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "UPDATE maquina SET status = 'REMOVIDA' WHERE codigo = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            conexao.close();
            
        } catch (SQLException e) {
            System.out.print("Não foi possivel realizar tal ação: " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

