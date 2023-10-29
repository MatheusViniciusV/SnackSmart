/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.ContratoDTO;
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
    public ContratoDTO getId(long id) throws ElementoNaoExisteException, SQLException {
        return null;
    }
    
    @Override
    public ArrayList<ContratoDTO> getTodos() {
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
    public void delete(ContratoDTO contrato) {
        
    }
    
    @Override
    public void atualizaContrato(ContratoDTO contrato) {
        
    }
    
    @Override
    public void atualizarStatus(long id, StatusContrato status) throws SQLException {
        
    }
    
    @Override
    public boolean validaAcessoLocatario(long id, String cpfLocatario) {
        return false;
    }
}
