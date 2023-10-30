package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.exceptions.dao.ElementoNaoExisteException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.idao.IContratosDAO;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class ContratosDAO implements IContratosDAO {
    @Override
    public ContratoDTO getId(long id) throws ElementoNaoExisteException, ClassNotFoundException, SQLException {
        ContratoDTO contrato = null;
        
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM contratos WHERE id = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            if(rs.next()) {
                contrato = new ContratoDTO(
                        rs.getLong("id"),
                        rs.getDouble("valor"),
                        new LocatarioDTO(),
                        new MaquinaDTO(),
                        LocalDate.parse(rs.getString("data_incio"), formatter),
                        LocalDate.parse(rs.getString("data_expiracao"), formatter),
                        LocalDate.parse(rs.getString("data_pagamento"), formatter),
                        rs.getString("observacoes")
                );
            } else {
                throw new ElementoNaoExisteException("Contrato" + id + " não existe.");
            }
            
            rs.close();
            preparedStatement.close();
            conexao.close();
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
           throw ex;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
//        return contrato;
    }
    
    @Override
    public ArrayList<ContratoDTO> getTodos() throws SQLException {
        ArrayList<ContratoDTO> contratos = null;
        
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM contratos";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            if(rs.next()) {
                ContratoDTO contrato = new ContratoDTO(
                        rs.getLong("id"),
                        rs.getDouble("valor"),
                        new LocatarioDTO(),
                        new MaquinaDTO(),
                        LocalDate.parse(rs.getString("data_incio"), formatter),
                        LocalDate.parse(rs.getString("data_expiracao"), formatter),
                        LocalDate.parse(rs.getString("data_pagamento"), formatter),
                        rs.getString("observacoes")
                );
                
                contratos.add(contrato);
            } 
            
            rs.close();
            preparedStatement.close();
            conexao.close();
        }  catch (PersistenciaException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
//        return contratos;
    }
    
    @Override
    public ArrayList<ContratoDTO> filtra(String locatarioCpf) throws LocatarioInvalidoException, SQLException {
        ArrayList<ContratoDTO> contratos = null;
        
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM contratos WHERE locatario_cpf = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, locatarioCpf);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            if(rs.next()) {
                ContratoDTO contrato = new ContratoDTO(
                        rs.getLong("id"),
                        rs.getDouble("valor"),
                        new LocatarioDTO(),
                        new MaquinaDTO(),
                        LocalDate.parse(rs.getString("data_incio"), formatter),
                        LocalDate.parse(rs.getString("data_expiracao"), formatter),
                        LocalDate.parse(rs.getString("data_pagamento"), formatter),
                        rs.getString("observacoes")
                );
                
                contratos.add(contrato);
            } 
            
            rs.close();
            preparedStatement.close();
            conexao.close();
        }  catch (PersistenciaException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
//        return contratos;

    }
    
    @Override
    public ArrayList<ContratoDTO> filtra(StatusContrato status) throws LocatarioInvalidoException, SQLException {
        ArrayList<ContratoDTO> contratos = null;
        
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM contratos WHERE status = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, status.toInt());
            ResultSet rs = preparedStatement.executeQuery();
            
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            if(rs.next()) {
                ContratoDTO contrato = new ContratoDTO(
                        rs.getLong("id"),
                        rs.getDouble("valor"),
                        new LocatarioDTO(),
                        new MaquinaDTO(),
                        LocalDate.parse(rs.getString("data_incio"), formatter),
                        LocalDate.parse(rs.getString("data_expiracao"), formatter),
                        LocalDate.parse(rs.getString("data_pagamento"), formatter),
                        rs.getString("observacoes")
                );
                
                contratos.add(contrato);
            } 
            
            rs.close();
            preparedStatement.close();
            conexao.close();
        }  catch (PersistenciaException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
//        return contratos;
    }
    
    @Override
    public void registraContrato(ContratoDTO contrato) {
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            // TODO conferir tabela
            String sql = "INSERT INTO cliente "
                    + "(nome, cpf, datanasc, endereco, bairro, cidade_id, fone, email)"
                    + " VALUES(?,?,?,?,?,?,?,?)"
                    + " RETURNING id";
            
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();

            Long id = null;
            if (rs.next()) {
                id = rs.getLong("id");
                contrato.setId(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public boolean delete(ContratoDTO contrato) {
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, contrato.getId());
            pstmt.executeUpdate();
            
            pstmt.close();
            conexao.close();
            
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public void atualizaContrato(ContratoDTO contrato) {
        try(Connection conexao = ConnectionManager.getInstance().getConnection()) {
            
            // TODO conferir tabela
            String sql = "UPDATE cliente "
                    + "   SET nome = ?, "
                    + "       cpf = ?, "
                    + "       datanasc = ?, "
                    + "       endereco = ?, "
                    + "       bairro = ?, "
                    + "       cidade_id = ?, "
                    + "       fone = ?, "
                    + "       email = ? "
                    + " WHERE id = ?;";
            
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.executeUpdate();
            
            conexao.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void atualizarStatus(long id, StatusContrato status) throws ClassNotFoundException, SQLException {
//        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "UPDATE cliente "
                    + "   SET nome = ?, "
                    + "       cpf = ?, "
                    + "       datanasc = ?, "
                    + "       endereco = ?, "
                    + "       bairro = ?, "
                    + "       cidade_id = ?, "
                    + "       fone = ?, "
                    + "       email = ? "
                    + " WHERE id = ?;";
//        }
    }
    
    public void atualizarStatus(ContratoDTO contrato, StatusContrato status) throws ClassNotFoundException, SQLException {
        try(Connection conexao = ConnectionManager.getInstance().getConnection()) {
            
            // TODO conferir tabela
            String sql = "UPDATE cliente "
                    + "   SET nome = ?, "
                    + "       cpf = ?, "
                    + "       datanasc = ?, "
                    + "       endereco = ?, "
                    + "       bairro = ?, "
                    + "       cidade_id = ?, "
                    + "       fone = ?, "
                    + "       email = ? "
                    + " WHERE id = ?;";
            
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            
            pstmt.executeUpdate();
            conexao.close();
        }
    }
    
    @Override
    public ContratoDTO getIdLocatario(long id, String locatarioCpf) throws ElementoNaoExisteException, ClassNotFoundException, SQLException {
        
        
        
        ContratoDTO contrato = null;
        
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM contratos WHERE id = ? AND locatario_cpf = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, locatarioCpf);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            if(rs.next()) {
                contrato = new ContratoDTO(
                        rs.getLong("id"),
                        rs.getDouble("valor"),
                        new LocatarioDTO(),
                        new MaquinaDTO(),
                        LocalDate.parse(rs.getString("data_incio"), formatter),
                        LocalDate.parse(rs.getString("data_expiracao"), formatter),
                        LocalDate.parse(rs.getString("data_pagamento"), formatter),
                        rs.getString("observacoes")
                );
            } else {
                throw new ElementoNaoExisteException("Contrato" + id + " não encontrado ou locatario " + locatarioCpf + " invalido.");
            }
            
            rs.close();
            preparedStatement.close();
            conexao.close();
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
           throw ex;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
