/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.ContratosDTO;
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
    ContratosDTO getId(long id) throws ElementoNaoExisteException, SQLException;
    
    ArrayList<ContratosDTO> getTodos();
    
    ArrayList<ContratosDTO> filtra(LocatarioDTO locatario) throws LocatarioInvalidoException, SQLException;
    
    void registraContrato(ContratosDTO contrato);
    
    void delete(long id);
    
    void delete(ContratosDTO contrato);
    
    void atualizaContrato(ContratosDTO contrato);
    
    //long proximoId();
    
    //boolean idUnico(long id);
    
    void atualizarStatus(long id, StatusContrato status) throws SQLException;
    
    boolean validaAcessoLocatario(long id, String cpfLocatario);
}
