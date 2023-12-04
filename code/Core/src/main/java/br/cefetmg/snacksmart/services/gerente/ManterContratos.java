/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.services.gerente;

import br.cefetmg.snacksmart.dao.ContratosDAO;
import br.cefetmg.snacksmart.dao.GerenteDAO;
import br.cefetmg.snacksmart.dao.MaquinaDAO;
import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.exceptions.dao.ElementoNaoExisteException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.idao.IContratosDAO;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import br.cefetmg.snacksmart.utils.enums.TiposOrdenacaoContrato;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author eloym
 */
public class ManterContratos {
    private IContratosDAO dao;
    private IGerenteDAO daoGerente;
    private MaquinaDAO daoMaquina;
    
    public ManterContratos() {
        dao = new ContratosDAO();
        daoGerente = new GerenteDAO();
        daoMaquina = new MaquinaDAO();
    }
    
    public ArrayList<ContratoDTO> getContratos() throws LocatarioInvalidoException, SQLException {
        ArrayList<ContratoDTO> contratos = dao.listaTodos();
        
        return contratos;
    }
    
    public ArrayList<ContratoDTO> filtraContratos(LocatarioDTO locatario, StatusContrato status, TiposOrdenacaoContrato ordenacao) throws LocatarioInvalidoException, SQLException {
        ArrayList<ContratoDTO> contratos;

        if(status != null) {
            if(locatario != null) {
                contratos = dao.filtra(locatario, status, ordenacao);
            } else {
                contratos = dao.filtra(status, ordenacao);
            }
        } else {
            if(locatario != null) {
                contratos = dao.filtra(locatario, ordenacao);
            } else {
                contratos = dao.filtra(ordenacao);
            }
        }

        return contratos;
    }
    
    public ArrayList<ContratoDTO> filtraContratos(StatusContrato status, TiposOrdenacaoContrato ordenacao) throws LocatarioInvalidoException, SQLException {
        ArrayList<ContratoDTO> contratos = dao.filtra(status, ordenacao);
        
        return contratos;
    }

    public ArrayList<ContratoDTO> filtraContratos(LocatarioDTO locatario, TiposOrdenacaoContrato ordenacao) throws LocatarioInvalidoException, SQLException {
        ArrayList<ContratoDTO> contratos = dao.filtra(locatario, ordenacao);

        return contratos;
    }
    
    public ContratoDTO getContrato(int id) throws SQLException, ElementoNaoExisteException {
        ContratoDTO contrato = dao.consultarPorId(id);
        
        return contrato;
    }
    
    public void cancelarContrato(int id) throws ClassNotFoundException, SQLException, PersistenciaException {
        ContratoDTO contrato = dao.consultarPorId(id);
        
        if(contrato.getStatus() == StatusContrato.VIGENTE || contrato.getStatus() == StatusContrato.CANCELAMENTO_SOLICITADO) {
            dao.atualizarStatus(id, StatusContrato.CANCELADO);

            MaquinaDTO maquina = contrato.getMaquina();
            maquina.setStatus(StatusMaquina.DISPONIVEL);
            daoMaquina.atualizarMaquina(maquina);
        }
    }
    
    public void validaContrato (int id) throws ClassNotFoundException, SQLException, PersistenciaException{
        ContratoDTO contrato = dao.consultarPorId(id);
        
        if(contrato.getStatus() == StatusContrato.INATIVO){
            MaquinaDTO maquina = contrato.getMaquina();
            if(maquina.getStatus() != StatusMaquina.ALUGADA){
                dao.atualizarStatus(id, StatusContrato.VIGENTE);
                maquina.setStatus(StatusMaquina.ALUGADA);
                daoMaquina.atualizarMaquina(maquina);
            }
            
        }
        
    
    }
    
    public ContratoDTO criarContrato(ContratoDTO contrato) throws PersistenciaException {
        try {
            ContratoDTO novoContrato = dao.registraContrato(contrato);

            MaquinaDTO maquina = novoContrato.getMaquina();
            maquina.setStatus(StatusMaquina.ALUGADA);
            maquina.setLocatarioResponsavel(novoContrato.getLocatario());
            daoMaquina.atualizarMaquina(maquina);

            return novoContrato;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
