package br.cefetmg.snacksmart.services.gerente;

import br.cefetmg.snacksmart.dao.LocatarioDAO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.exceptions.dto.CPFInvalidoException;
import br.cefetmg.snacksmart.exceptions.dto.ParametroInvalidoException;
import br.cefetmg.snacksmart.utils.InputValidador;

import java.util.ArrayList;

public class ManterLocatarios {
    private LocatarioDAO dao;

    public ManterLocatarios() {
        dao = new LocatarioDAO();
    }

    public LocatarioDTO buscaPorCpf(String cpf) throws PersistenciaException, ParametroInvalidoException {
        if(!InputValidador.validaCPF(cpf))
            throw new CPFInvalidoException();

        LocatarioDTO locatario = null;

        locatario = dao.consultarPorCPF(cpf);

        return locatario;
    }

    public ArrayList<LocatarioDTO> listaLocatarios() throws PersistenciaException {
        return dao.listarTodos();
    }

    public int registrar(LocatarioDTO locatarioDTO) throws PersistenciaException {
        return dao.inserir(locatarioDTO);
    }
}
