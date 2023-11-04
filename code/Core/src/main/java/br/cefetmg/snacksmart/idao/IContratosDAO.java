/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.dao.ElementoNaoExisteException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eloym
 */
public interface IContratosDAO {
    ContratoDTO  consultarPorId(int id) throws ElementoNaoExisteException, SQLException;
    
    ContratoDTO consultarPorIdLocatario(int id, LocatarioDTO locatario) throws SQLException;
    
    ArrayList<ContratoDTO> listaTodos() throws SQLException;
    
    ArrayList<ContratoDTO> filtra(LocatarioDTO locatario) throws LocatarioInvalidoException, SQLException;
    
    ArrayList<ContratoDTO> filtra(StatusContrato status) throws LocatarioInvalidoException, SQLException;
    
    ContratoDTO registraContrato(ContratoDTO contrato) throws SQLException;
    
    void deletarPorId(int id) throws SQLException;

    
    //long proximoId();
    
    //boolean idUnico(long id);
    
    void atualizarStatus(int id, StatusContrato status) throws SQLException;
}
