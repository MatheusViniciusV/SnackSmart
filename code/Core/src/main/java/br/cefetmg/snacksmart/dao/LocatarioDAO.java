package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.idao.ILocatarioDAO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;

public class LocatarioDAO implements ILocatarioDAO {
    
    @Override
    public int inserir(LocatarioDTO locatarioDTO) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
         
            String sql = "INSERT INTO locatario (nome, senha, cpf, rg, telefone, email) "
                       + "VALUES (?, ?, ?, ?, ?, ?) ";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, locatarioDTO.getNome());
            pstmt.setString(2, locatarioDTO.getSenha());
            pstmt.setString(3, locatarioDTO.getCPF());
            pstmt.setString(4, locatarioDTO.getRG());
            pstmt.setString(5, locatarioDTO.getTelefone());
            pstmt.setString(6, locatarioDTO.getEmail());
            pstmt.executeUpdate();
            
            sql = "SELECT `pk` FROM `locatario` ORDER BY `pk` DESC LIMIT 1";
            
            pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            int pk = 0;
            if (rs.next()) {
                pk = rs.getInt("pk");
                locatarioDTO.setId(pk);
            }

            pstmt.close();
            connection.close();

            return pk;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public boolean atualizar(LocatarioDTO locatarioDTO) throws PersistenciaException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE locatario "
                    + "   SET nome = ?, "
                    + "       senha = ?, "
                    + "       cpf = ?, "
                    + "       rg = ?, "
                    + "       telefone = ?, "
                    + "       email = ? "
                    + " WHERE pk = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, locatarioDTO.getNome());
            pstmt.setString(2, locatarioDTO.getSenha());
            pstmt.setString(3, locatarioDTO.getCPF());
            pstmt.setString(4, locatarioDTO.getRG());
            pstmt.setString(5, locatarioDTO.getTelefone());
            pstmt.setString(6, locatarioDTO.getEmail());
            pstmt.setInt(7, locatarioDTO.getId());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public boolean delete(LocatarioDTO locatarioDTO) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM locatario WHERE pk = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, locatarioDTO.getId());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public ArrayList<LocatarioDTO> listarTodos() throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM locatario ORDER BY pk";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<LocatarioDTO> listAll = null;
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    LocatarioDTO locatarioDTO = new LocatarioDTO();
                    locatarioDTO.setId(rs.getInt("pk"));
                    locatarioDTO.setNome(rs.getString("nome"));
                    locatarioDTO.setSenha(rs.getString("senha"));
                    locatarioDTO.setCPF(rs.getString("CPF"));
                    locatarioDTO.setRG(rs.getString("RG"));
                    locatarioDTO.setTelefone(rs.getString("telefone"));
                    locatarioDTO.setEmail(rs.getString("email"));
                    listAll.add(locatarioDTO);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public LocatarioDTO consultarPorId(int id) throws PersistenciaException {

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM locatario WHERE pk = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            LocatarioDTO locatarioDTO = null;
            if (rs.next()) {
                locatarioDTO = new LocatarioDTO();
                locatarioDTO.setId(rs.getInt("pk"));
                locatarioDTO.setNome(rs.getString("nome"));
                locatarioDTO.setSenha(rs.getString("senha"));
                locatarioDTO.setCPF(rs.getString("CPF"));
                locatarioDTO.setRG(rs.getString("RG"));
                locatarioDTO.setTelefone(rs.getString("telefone"));
                locatarioDTO.setEmail(rs.getString("email"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return locatarioDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public LocatarioDTO consultarPorCPF(String CPF) throws PersistenciaException {

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM locatario WHERE `cpf` = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, CPF);
            ResultSet rs = pstmt.executeQuery();

            LocatarioDTO locatarioDTO = null;
            if (rs.next()) {
                locatarioDTO = new LocatarioDTO();
                locatarioDTO.setId(rs.getInt("pk"));
                locatarioDTO.setNome(rs.getString("nome"));
                locatarioDTO.setSenha(rs.getString("senha"));
                locatarioDTO.setCPF(rs.getString("CPF"));
                locatarioDTO.setRG(rs.getString("RG"));
                locatarioDTO.setTelefone(rs.getString("telefone"));
                locatarioDTO.setEmail(rs.getString("email"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return locatarioDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
