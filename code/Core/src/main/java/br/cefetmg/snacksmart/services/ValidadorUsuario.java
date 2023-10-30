/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.services;

import br.cefetmg.snacksmart.dao.GerenteDAO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
//        GerenteDTO gerente = daoGerente.get();
        if(true)
            return TipoUsuario.LOCADOR;
//        
//        // * validar se é locador
//        if(cpf.equals(gerente.getCPF()))
//            return TipoUsuario.LOCATARIO;
        
        
        
        return TipoUsuario.NAO_CADASTRADO;
    }
    
    public boolean validarGerente(String cpf, String token)   
            throws UnsupportedEncodingException, NoSuchAlgorithmException, PersistenciaException {
        
//        GerenteDTO gerente = daoGerente.get();

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte hash[] = algorithm.digest(token.getBytes("UTF-8"));

        StringBuilder aux = new StringBuilder();
        for (byte b : hash) {
            aux.append(String.format("%02X", 0xFF & b));
        }
        token = aux.toString();

//        return (token.equals(gerente.getToken()) && cpf.equals(gerente.getCPF()));

        return true;
    }
    
    public boolean validarLocatario(String cpf, String token) 
            throws UnsupportedEncodingException, NoSuchAlgorithmException, PersistenciaException {
        
//        GerenteDTO gerente = daoGerente.get();

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte hash[] = algorithm.digest(token.getBytes("UTF-8"));

        StringBuilder aux = new StringBuilder();
        for (byte b : hash) {
            aux.append(String.format("%02X", 0xFF & b));
        }
        token = aux.toString();

//        return (token.equals(gerente.getToken()) && cpf.equals(gerente.getCPF()));

        return true;
    }
}
