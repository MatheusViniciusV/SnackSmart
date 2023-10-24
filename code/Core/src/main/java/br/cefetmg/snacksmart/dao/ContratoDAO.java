/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.idao.IContratosDAO;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class ContratoDAO implements IContratosDAO {
    @Override
    public ContratoDTO obterPorID(long id) {
        return null;
    }
    
    @Override
    public ArrayList<ContratoDTO> obterTodos() {
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
}
