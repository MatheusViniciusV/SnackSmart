/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.services;

import br.cefetmg.snacksmart.dao.GerenteDAO;
import br.cefetmg.snacksmart.dao.LocatarioDAO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.dto.IUsuarioDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.idao.ILocatarioDAO;
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
    ILocatarioDAO daoLocatario;
    
    public ValidadorUsuario() {
        daoGerente = new GerenteDAO();
        daoLocatario = new LocatarioDAO();
    }
    
    public TipoUsuario tipoUsuario(String cpf) throws PersistenciaException {
        TipoUsuario tipoUsuario;
        
        GerenteDTO gerente = daoGerente.get();
        if(gerente.getCPF().equals(cpf))
            return TipoUsuario.LOCADOR;

        LocatarioDTO locatario = daoLocatario.consultarPorCPF(cpf);
//        // * validar se é locador
        if(locatario != null)
            return TipoUsuario.LOCATARIO;



        return TipoUsuario.NAO_CADASTRADO;
    }
    
    public boolean validarGerente(String cpf, String senha)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, PersistenciaException {
        
        GerenteDTO gerente = daoGerente.get();

        senha = SenhaManager.fazHash(senha);

        return (senha.equals(gerente.getSenha()) && cpf.equals(gerente.getCPF()));
    }
    
    public boolean validarLocatario(String cpf, String senha)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, PersistenciaException {
        
        LocatarioDTO locatario = daoLocatario.consultarPorCPF(cpf);
        

        senha = SenhaManager.fazHash(senha);

        System.out.println(locatario.getSenha());
        System.out.println(senha);
        boolean x = (senha.equals(locatario.getSenha()) && cpf.equals(locatario.getCPF()));

        return x;
    }

    public IUsuarioDTO getGenrente() {
        try {
            return daoGerente.get();
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }

    public IUsuarioDTO getLocatario(String cpf) {
        try {
            return daoLocatario.consultarPorCPF(cpf);
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }
}
