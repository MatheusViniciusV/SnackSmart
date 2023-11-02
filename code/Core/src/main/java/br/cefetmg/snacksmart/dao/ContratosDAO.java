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
import br.cefetmg.snacksmart.utils.DataManager;
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
    public ContratoDTO consultarPorId(int id) throws SQLException {
        ConnectionManager conn = ConnectionManager.getInstance();
        ContratoDTO contrato = null;
        try {
            Connection conexao =  conn.getConnection();

            String sql = "SELECT * FROM `contrato` WHERE pk = ?";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                LocatarioDAO daoLocatario = new LocatarioDAO();
                MaquinaDAO daoMaquina = new MaquinaDAO();

                contrato = new ContratoDTO(
                        rs.getInt("pk"),
                        rs.getDouble("valor"),
                        daoLocatario.consultarPorId(rs.getInt("locatario__fk")),
                        null,
                        new DataManager(rs.getDate("data_inicio")),
                        new DataManager(rs.getDate("data_fim")),
                        new DataManager(rs.getDate("data_pagamento")),
                        rs.getString("observacoes")
                );
                System.out.println("contrato pego do banco de dados");
            }

            rs.close();
            pstmt.close();
            conexao.close();
        } catch (ClassNotFoundException | PersistenciaException e) {
            throw new RuntimeException(e);
        }

        return contrato;
    }
    
    @Override
    public ArrayList<ContratoDTO> listaTodos() throws SQLException {
        ArrayList<ContratoDTO> contratos = new ArrayList<ContratoDTO>();

        try {
            Connection conexao =  ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM `contrato` ORDER BY `pk`";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LocatarioDAO daoLocatario = new LocatarioDAO();
                ContratoDTO contrato = null;

                contrato = new ContratoDTO(
                        rs.getInt("pk"),
                        rs.getDouble("valor"),
                        daoLocatario.consultarPorId(rs.getInt("locatario__fk")),
                        null,
                        new DataManager(rs.getDate("data_inicio")),
                        new DataManager(rs.getDate("data_fim")),
                        new DataManager(rs.getDate("data_pagamento")),
                        rs.getString("observacoes")
                );

                contratos.add(contrato);
            }

            rs.close();
            pstmt.close();
            conexao.close();
        } catch (PersistenciaException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return contratos;
    }
    
    @Override
    public ArrayList<ContratoDTO> filtra(LocatarioDTO locatario) throws LocatarioInvalidoException, SQLException {
        ArrayList<ContratoDTO> contratos = new ArrayList<ContratoDTO>();

        try {
            Connection conexao =  ConnectionManager.getInstance().getConnection();

            String sql = "SELECT *  FROM `contrato` WHERE `locatario__fk` = ? ORDER BY `pk`";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, locatario.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LocatarioDAO daoLocatario = new LocatarioDAO();
                ContratoDTO contrato = null;

                contrato = new ContratoDTO(
                        rs.getInt("pk"),
                        rs.getDouble("valor"),
                        daoLocatario.consultarPorId(rs.getInt("locatario__fk")),
                        null,
                        new DataManager(rs.getDate("data_inicio")),
                        new DataManager(rs.getDate("data_fim")),
                        new DataManager(rs.getDate("data_pagamento")),
                        rs.getString("observacoes")
                );

                contratos.add(contrato);
            }

            rs.close();
            pstmt.close();
            conexao.close();
        } catch (PersistenciaException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return contratos;
    }
    
    @Override
    public ArrayList<ContratoDTO> filtra(StatusContrato status) throws LocatarioInvalidoException, SQLException {
        ArrayList<ContratoDTO> contratos = new ArrayList<ContratoDTO>();

        try {
            Connection conexao =  ConnectionManager.getInstance().getConnection();

            String sql = "SELECT *  FROM `contrato` WHERE `estado` = ? ORDER BY `pk`";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, status.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LocatarioDAO daoLocatario = new LocatarioDAO();
                ContratoDTO contrato = null;

                contrato = new ContratoDTO(
                        rs.getInt("pk"),
                        rs.getDouble("valor"),
                        daoLocatario.consultarPorId(rs.getInt("locatario__fk")),
                        null,
                        new DataManager(rs.getDate("data_inicio")),
                        new DataManager(rs.getDate("data_fim")),
                        new DataManager(rs.getDate("data_pagamento")),
                        rs.getString("observacoes")
                );

                contratos.add(contrato);
            }

            rs.close();
            pstmt.close();
            conexao.close();
        } catch (PersistenciaException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return contratos;
    }
    
    @Override
    public void registraContrato(ContratoDTO contrato) {
        ConnectionManager conn = ConnectionManager.getInstance();

        try {
            Connection conexao =  conn.getConnection();

            String sql = """
            INSERT INTO `contrato`(
                `observacoes`,
                `data_inicio`,
                `data_fim`,
                `data_pagamento`,
                `valor`,
                `gerente__fk`,
                `locatario__fk`,
                `maquina__fk`,
                `estado`
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
            """;

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, contrato.getObservacoes());
            pstmt.setDate(2, contrato.getDataInicio().getSqlDate());
            pstmt.setDate(3, contrato.getDataFim().getSqlDate());
            pstmt.setDate(4, contrato.getDataPagamento().getSqlDate());
            pstmt.setDouble(5, contrato.getValorPagamento());
            pstmt.setInt(6, 1);
            pstmt.setInt(7, contrato.getLocatario().getId());
//            pstmt.setInt(8, contrato.getMaquina().getId);
            pstmt.setInt(8, 1);
            pstmt.setString(9, contrato.getStatus().toString());
            pstmt.executeUpdate();

            pstmt.close();
            conexao.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void deletarPorId(int id) {
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM `contrato` WHERE pk = ?";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            pstmt.close();
            conexao.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizarStatus(int id, StatusContrato status) throws SQLException {
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE `contrato` SET `estado` = ? WHERE `pk` = ?";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, status.toString());
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            pstmt.close();
            conexao.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public ContratoDTO consultarPorIdLocatario(int id, LocatarioDTO locatario) throws SQLException {
        ContratoDTO contrato = null;
        
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM `contrato` WHERE `pk` = ? AND `locatario__fk` = ?";
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, locatario.getId());
            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next()) {
                contrato = new ContratoDTO(
                        rs.getInt("pk"),
                        rs.getDouble("valor"),
                        locatario,
                        null,
                        new DataManager(rs.getDate("data_inicio")),
                        new DataManager(rs.getDate("data_fim")),
                        new DataManager(rs.getDate("data_pagamento")),
                        rs.getString("observacoes")
                );
            }
            
            rs.close();
            preparedStatement.close();
            conexao.close();
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        } catch (PersistenciaException ex) {
            Logger.getLogger(ContratosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return contrato;
    }
}
