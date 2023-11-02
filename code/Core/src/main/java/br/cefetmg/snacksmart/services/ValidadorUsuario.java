/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.services;

import br.cefetmg.snacksmart.dao.GerenteDAO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.dto.IUsuarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.utils.SenhaManager;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author eloym
 */
public class ValidadorUsuario {
    IGerenteDAO daoGerente;
    
    public ValidadorUsuario() {
        daoGerente = new GerenteDAO();
    }
    
    public TipoUsuario tipoUsuario(String cpf) throws PersistenciaException {
        TipoUsuario tipoUsuario;
        
        GerenteDTO gerente = daoGerente.get();
        if(gerente.getCPF().equals(cpf))
            return TipoUsuario.LOCADOR;
//        
//        // * validar se é locador
//        if(cpf.equals(gerente.getCPF()))
//            return TipoUsuario.LOCATARIO;
        
        
        
        return TipoUsuario.LOCATARIO;
    }
    
    public boolean validarGerente(String cpf, String senha)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, PersistenciaException {
        
        GerenteDTO gerente = daoGerente.get();

        senha = SenhaManager.fazHash(senha);

        return (senha.equals(gerente.getSenha()) && cpf.equals(gerente.getCPF()));
    }
    
    public boolean validarLocatario(String cpf, String senha)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, PersistenciaException {
        
//        LocatarioDTO locatario = ...;

        senha = SenhaManager.fazHash(senha);

//        return (token.equals(locatario.getSenha()) && cpf.equals(locatario.getCPF()));

        return true;
    }

    public IUsuarioDTO getGenrente() {
        try {
            return daoGerente.get();
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }
}
