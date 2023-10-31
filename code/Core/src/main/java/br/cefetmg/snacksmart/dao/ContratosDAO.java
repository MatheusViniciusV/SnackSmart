/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.exceptions.dao.ElementoNaoExisteException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.idao.IContratosDAO;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;

import java.sql.*;
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
    public ContratoDTO getId(long id) throws ElementoNaoExisteException, SQLException {
        ConnectionManager conn = ConnectionManager.getInstance();
        ContratoDTO contrato = null;
        try {
            Connection conexao =  conn.getConnection();

            String sql = "SELECT * FROM locatario WHERE pk = ?";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                contrato = new ContratoDTO(
                        rs.getLong("pk"),
                        rs.getInt("valor"),
                        rs.getDate("data-inicio").toLocalDate(),
                        rs.getDate("data-fim").toLocalDate(),
                        rs.getDate("data-pagamento").toLocalDate(),
                        rs.getString("observacoes")
                );
                System.out.println("contrato pego do banco de dados");
            }

            rs.close();
            pstmt.close();
            conexao.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }

        return contrato;
    }
    
    @Override
    public ArrayList<ContratoDTO> listaTodos() throws SQLException {
        ArrayList<ContratoDTO> contratos = null;

        ConnectionManager conn = ConnectionManager.getInstance();
        try {
            Connection conexao =  conn.getConnection();

            String sql = "SELECT * FROM contrato ORDER BY pk";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            LocatarioDA
            while (rs.next()) {
                ContratoDTO contrato = null;
                contrato = new ContratoDTO(
                        rs.getLong("pk");

                )
            }
        }

        return contratos

    }
    
    @Override
    public ArrayList<ContratoDTO> filtra(LocatarioDTO locatario) throws LocatarioInvalidoException, SQLException {
        return null;
    }
    
    @Override
    public ArrayList<ContratoDTO> filtra(StatusContrato status) throws LocatarioInvalidoException, SQLException {
        return null;
    }
    
    @Override
    public void registraContrato(ContratoDTO contrato) {
        ConnectionManager conn = ConnectionManager.getInstance();

        GerenteDTO gerente = contrato.getGerente();
        LocatarioDTO locatario = contrato.getLocatario();

        try {
            Connection conexao =  conn.getConnection();

            String sql = "INSERT INTO `contrato` (`data_inicio`, `data_fim`, `gerente__fk`, `locatario__fk`, `estado`)" +
                    " VALUES (?, ?, ?, ?, ?);";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            Date dataInicio = Date.valueOf(contrato.getDataInicio());
            pstmt.setDate(1, dataInicio);
            Date dataFim = Date.valueOf(contrato.getDataExpiracao());
            pstmt.setDate(2, dataFim);
            pstmt.setInt(3, 1);
            pstmt.setLong(4, locatario.getPk());
            pstmt.setString(5, contrato.getStatus().toString());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void delete(long id) {
        
    }
    
    @Override
    public void atualizaContrato(ContratoDTO contrato) {
        
    }
    
    @Override
    public void atualizarStatus(long id, StatusContrato status) throws ClassNotFoundException, SQLException {
        
    }
    
    @Override
    public ContratoDTO getIdLocatario(long id, String locatarioCpf) throws ClassNotFoundException, SQLException {
        
        
        boolean retorno = false;
        ContratoDTO contrato = null;
        
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM contratos WHERE id = ? AND locatario_cpf = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, locatarioCpf);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            retorno = rs.next();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            if(rs.next()) {
                contrato = new ContratoDTO(
                        rs.getLong("id"),
                        rs.getDouble("valor"),
//                        new LocatarioDTO(),
//                        new MaquinaDTO(),
                        LocalDate.parse(rs.getString("data_incio"), formatter),
                        LocalDate.parse(rs.getString("data_expiracao"), formatter),
                        LocalDate.parse(rs.getString("data_pagamento"), formatter),
                        rs.getString("observacoes")
                );
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
