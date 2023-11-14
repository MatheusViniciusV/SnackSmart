package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import java.util.ArrayList;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoMaquina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* @author Arthur Milagres  */

public class MaquinaDAO implements IMaquinaDAO {    
    @Override
    public MaquinaDTO acessarMaquina(int codigo) throws PersistenciaException {            
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM `maquina` WHERE pk = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                InputStream imagem = resultSet.getBinaryStream("imagem");
                TipoMaquina tipo = TipoMaquina.valueOf(resultSet.getString("tipo"));
                String localizacao = resultSet.getString("localizacao");
                
                int locatarioId = resultSet.getInt("locatario__fk");               
                LocatarioDAO locatarioDAO = new LocatarioDAO();
                LocatarioDTO locatario = locatarioDAO.consultarPorId(locatarioId);
                StatusMaquina status = StatusMaquina.valueOf(resultSet.getString("status"));
                MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, imagem, tipo, localizacao, locatario, status);
                
                resultSet.close();
                preparedStatement.close();
                conexao.close();
                
                return maquinaDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }       
        return null;
    }

    @Override
    public MaquinaDTO acessarMaquina(int codigo, StatusMaquina status) throws PersistenciaException {
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM `maquina` WHERE `pk` = ? AND `status` = ?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, codigo);
            preparedStatement.setString(2, status.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                InputStream imagem = resultSet.getBinaryStream("imagem");
                TipoMaquina tipo = TipoMaquina.valueOf(resultSet.getString("tipo"));
                String localizacao = resultSet.getString("localizacao");

                int locatarioId = resultSet.getInt("locatario__fk");
                LocatarioDAO locatarioDAO = new LocatarioDAO();
                LocatarioDTO locatario = locatarioDAO.consultarPorId(locatarioId);
                MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, imagem, tipo, localizacao, locatario, status);

                resultSet.close();
                preparedStatement.close();
                conexao.close();

                return maquinaDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public MaquinaDTO acessarMaquinaTipoStatus(TipoMaquina tipo, StatusMaquina status) throws PersistenciaException {
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM `maquina` WHERE `tipo` = ? AND `status` = ?";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, tipo.toString());
            preparedStatement.setString(2, status.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int codigo = resultSet.getInt("pk");
                String nome = resultSet.getString("nome");
                InputStream imagem = resultSet.getBinaryStream("imagem");
                String localizacao = resultSet.getString("localizacao");

                int locatarioId = resultSet.getInt("locatario__fk");
                LocatarioDAO locatarioDAO = new LocatarioDAO();
                LocatarioDTO locatario = locatarioDAO.consultarPorId(locatarioId);
                MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, imagem, tipo, localizacao, locatario, status);

                resultSet.close();
                preparedStatement.close();
                conexao.close();

                return maquinaDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return null;
    }
    
    @Override
    public ArrayList<MaquinaDTO> acessarTodasMaquinas() throws PersistenciaException {
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM maquina WHERE status != 'REMOVIDA'";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                                 
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<MaquinaDTO> maquinasVetor = null; 
            if (resultSet.next()) {
                maquinasVetor = new ArrayList<>();
                do {
                    int codigo = resultSet.getInt("codigo");
                    String nome = resultSet.getString("nome");
                    InputStream imagem = resultSet.getBinaryStream("imagem");
                    TipoMaquina tipo = TipoMaquina.valueOf(resultSet.getString("tipo"));
                    String localizacao = resultSet.getString("localizacao");

                    int locatarioId = resultSet.getInt("locatario__fk");
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
            
        }  catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }   
    
    @Override
    public ArrayList<MaquinaDTO> acessarTodasMaquinas(int locatarioId) throws PersistenciaException {
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM maquina WHERE locatario__fk = ? AND status != 'REMOVIDA'";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, locatarioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<MaquinaDTO> maquinasVetor = null; 
            if (resultSet.next()) {
                maquinasVetor = new ArrayList<>();
                do {
                    int codigo = resultSet.getInt("codigo");
                    String nome = resultSet.getString("nome");
                    InputStream imagem = resultSet.getBinaryStream("imagem");
                    TipoMaquina tipo = TipoMaquina.valueOf(resultSet.getString("tipo"));
                    String localizacao = resultSet.getString("localizacao");

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
            
        }  catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    } 
    
    public ArrayList<MaquinaDTO> acessarTodasMaquinasSemExcecoes() throws PersistenciaException {
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM maquina";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                                 
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<MaquinaDTO> maquinasVetor = null; 
            if (resultSet.next()) {
                maquinasVetor = new ArrayList<>();
                do {
                    int codigo = resultSet.getInt("codigo");
                    String nome = resultSet.getString("nome");
                    InputStream imagem = resultSet.getBinaryStream("imagem");
                    TipoMaquina tipo = TipoMaquina.valueOf(resultSet.getString("tipo"));
                    String localizacao = resultSet.getString("localizacao");

                    int locatarioId = resultSet.getInt("locatario__fk");
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
            
        }  catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public void adicionarMaquina(MaquinaDTO maquina) throws PersistenciaException {      
        try{
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "INSERT INTO maquina (nome, codigo, imagem, tipo, localizacao, locatario__fk, status, aluguel) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                    
            preparedStatement.setString(1, maquina.getNome());
            preparedStatement.setInt(2, maquina.getCodigo());
            preparedStatement.setBinaryStream(3, maquina.getImagem());
            preparedStatement.setString(4, maquina.getTipo().name());
            preparedStatement.setString(5, maquina.getLocalizacao());            
            preparedStatement.setInt(6, maquina.getLocatario().getId());           
            preparedStatement.setString(7, maquina.getStatus().name());
            preparedStatement.setDouble(8, 1);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conexao.close();
            
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
            preparedStatement.setBinaryStream(2, updatedMaquina.getImagem());
            preparedStatement.setString(3, updatedMaquina.getTipo().name());
            preparedStatement.setString(4, updatedMaquina.getLocalizacao());
            preparedStatement.setInt(5, updatedMaquina.getLocatario().getId());
            preparedStatement.setString(6, updatedMaquina.getStatus().name());
            preparedStatement.setInt(7, updatedMaquina.getCodigo());

            preparedStatement.executeUpdate();           
            preparedStatement.close();
            conexao.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
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
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
}

