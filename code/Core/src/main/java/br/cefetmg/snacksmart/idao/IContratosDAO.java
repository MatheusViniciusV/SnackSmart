/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import java.util.ArrayList;

/**
 *
 * @author eloym
 */
public interface IContratosDAO {
    ContratoDTO obterPorID(long id);
    
    ArrayList<ContratoDTO> obterTodos();
    
    void registraContrato(ContratoDTO contrato);
    
    void delete(long id);
    
    void delete(ContratoDTO contrato);
    
    void atualizaContrato(ContratoDTO contrato);
    
    //long proximoId();
    
    //boolean idUnico(long id);
}
