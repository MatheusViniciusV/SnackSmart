package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.idao.IFornecedorDAO;
import br.cefetmg.snacksmart.dto.FornecedorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.util.ArrayList;
import br.cefetmg.snacksmart.idao.ILocatarioDAO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;

public class FornecedorDAO implements IFornecedorDAO {
    
    @Override
    public int inserir(FornecedorDTO fornecedorDTO) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
         
            String sql = "INSERT INTO fornecedor (nome, telefone, email, locatario__fk) "
                       + "VALUES (?, ?, ?, ?) ";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, fornecedorDTO.getNome());
            pstmt.setString(2, fornecedorDTO.getTelefone());
            pstmt.setString(3, fornecedorDTO.getEmail());
            pstmt.setInt(4, fornecedorDTO.getLocatario().getId());
            pstmt.executeUpdate();
            
            sql = "SELECT `pk` FROM `fornecedor` ORDER BY `pk` DESC LIMIT 1";
            
            pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            int pk = 0;
            if (rs.next()) {
                pk = rs.getInt("pk");
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
    public boolean atualizar(FornecedorDTO fornecedorDTO) throws PersistenciaException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE fornecedor "
                    + "   SET nome = ?, "
                    + "       telefone = ?, "
                    + "       email = ?, "
                    + "       locatario__fk = ?, "
                    + " WHERE pk = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, fornecedorDTO.getNome());
            pstmt.setString(2, fornecedorDTO.getTelefone());
            pstmt.setString(3, fornecedorDTO.getEmail());
            pstmt.setInt(4, fornecedorDTO.getLocatario().getId());
            pstmt.setInt(5, fornecedorDTO.getId());
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
    public boolean delete(FornecedorDTO fornecedorDTO) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM fornecedor WHERE pk = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, fornecedorDTO.getId());
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
    public ArrayList<FornecedorDTO> listarTodos() throws PersistenciaException {
        try {
            ILocatarioDAO locatarioDAO = new LocatarioDAO();
            
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM fornecedor ORDER BY pk";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<FornecedorDTO> listAll = null;
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    FornecedorDTO fornecedorDTO = new FornecedorDTO();
                    fornecedorDTO.setId(rs.getInt("pk"));
                    fornecedorDTO.setNome(rs.getString("nome"));
                    fornecedorDTO.setTelefone(rs.getString("telefone"));
                    fornecedorDTO.setEmail(rs.getString("email"));
                    int locatarioFK = rs.getInt("locatario__fk");
                    fornecedorDTO.setLocatario(locatarioDAO.consultarPorId(locatarioFK));
                    listAll.add(fornecedorDTO);
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
    public ArrayList<FornecedorDTO> listarPorLocatario(String CPF) throws PersistenciaException {
        try {
            ILocatarioDAO locatarioDAO = new LocatarioDAO();
            LocatarioDTO locatarioDTO = locatarioDAO.consultarPorCPF(CPF);
            
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * " +
                         "FROM fornecedor " +
                         "JOIN locatario ON locatario.pk = fornecedor.locatario__fk " +
                         "WHERE locatario.cpf = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, CPF);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<FornecedorDTO> listAll = null;
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    FornecedorDTO fornecedorDTO = new FornecedorDTO();
                    fornecedorDTO.setId(rs.getInt("pk"));
                    fornecedorDTO.setNome(rs.getString("nome"));
                    fornecedorDTO.setTelefone(rs.getString("telefone"));
                    fornecedorDTO.setEmail(rs.getString("email"));
                    fornecedorDTO.setLocatario(locatarioDTO);
                    listAll.add(fornecedorDTO);
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
}
