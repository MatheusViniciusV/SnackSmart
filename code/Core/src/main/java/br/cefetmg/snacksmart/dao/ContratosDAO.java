/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    public ContratoDTO getId(long id) throws ElementoNaoExisteException, SQLException {
        return null;
    }
    
    @Override
    public ArrayList<ContratoDTO> getTodos() throws SQLException {
        return null;
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
                        new LocatarioDTO(),
                        new MaquinaDTO(),
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
