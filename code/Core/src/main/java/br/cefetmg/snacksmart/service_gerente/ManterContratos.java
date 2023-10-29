/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.service_gerente;

import br.cefetmg.snacksmart.dao.ContratosDAO;
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
 * @author eloym
 */
public class ManterContratos {
    private IContratosDAO dao;
    
    public ManterContratos() {
        dao = new ContratosDAO();
    }
    
    public ArrayList<ContratoDTO> getContratos() throws LocatarioInvalidoException, SQLException {
        ArrayList contratos = dao.getTodos();
        
        return contratos;
    }
    
    public ArrayList<ContratoDTO> getContratosAtivos() throws LocatarioInvalidoException, SQLException {
        ArrayList contratos = dao.filtra(StatusContrato.ATIVO);
        
        return contratos;
    }
    
    public ArrayList<ContratoDTO> filtraContratos(LocatarioDTO locatario) throws LocatarioInvalidoException, SQLException {
        ArrayList contratos = dao.filtra(locatario);
        
        return contratos;
    }
    
    public ArrayList<ContratoDTO> filtraContratos(StatusContrato status) throws LocatarioInvalidoException, SQLException {
        ArrayList contratos = dao.filtra(status);
        
        return contratos;
    }
    
    public ContratoDTO getContrato(long id) throws SQLException, ElementoNaoExisteException {
        ContratoDTO contrato = dao.getId(id);
        
        return contrato;
    }
    
    public void cancelarContrato(long id) throws ClassNotFoundException, SQLException {
        ContratoDTO contrato = dao.getId(id);
        
        if(contrato.getStatus() == StatusContrato.ATIVO || contrato.getStatus() == StatusContrato.CANCELAMENTO_SOLICITADO)
            dao.atualizarStatus(id, StatusContrato.CANCELADO);
    }
    
    public void criarContrato(ContratoDTO contrato) {
        
    }
}
