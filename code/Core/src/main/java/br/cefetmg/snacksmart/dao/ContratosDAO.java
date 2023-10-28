/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.ContratosDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.dao.ElementoNaoExisteException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.idao.IContratosDAO;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class ContratosDAO implements IContratosDAO {
    @Override
    public ContratosDTO getId(long id) throws ElementoNaoExisteException, SQLException {
        return null;
    }
    
    @Override
    public ArrayList<ContratosDTO> getTodos() {
        return null;
    }
    
    @Override
    public ArrayList<ContratosDTO> filtra(LocatarioDTO locatario) throws LocatarioInvalidoException, SQLException {
        return null;
    }
    
    @Override
    public void registraContrato(ContratosDTO contrato) {
        
    }
    
    @Override
    public void delete(long id) {
        
    }
    
    @Override
    public void delete(ContratosDTO contrato) {
        
    }
    
    @Override
    public void atualizaContrato(ContratosDTO contrato) {
        
    }
    
    @Override
    public void atualizarStatus(long id, StatusContrato status) throws SQLException {
        
    }
    
    @Override
    public boolean validaAcessoLocatario(long id, String cpfLocatario) {
        return false;
    }
}
