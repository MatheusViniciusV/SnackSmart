/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.services;

import br.cefetmg.snacksmart.dao.GerenteDAO;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;

/**
 *
 * @author eloym
 */
public class ValidadorUsuario {
    IGerenteDAO daoGerente;
    
    public ValidadorUsuario() {
        daoGerente = new GerenteDAO();
    }
    
    public TipoUsuario tipoUsuario(String cpf) {
        
        
        return TipoUsuario.NAO_CADASTRADO;
    }
}
