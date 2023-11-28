package br.cefetmg.snacksmart.services.gerente;

import br.cefetmg.snacksmart.dao.LocatarioDAO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.util.ArrayList;

public class ManterLocatarios {
    private LocatarioDAO dao;

    public ManterLocatarios() {
        dao = new LocatarioDAO();
    }

    public LocatarioDTO buscaPorCpf(String cpf) throws PersistenciaException {
        LocatarioDTO locatario = null;

        locatario = dao.consultarPorCPF(cpf);

        return locatario;
    }
    public ArrayList<LocatarioDTO> recuperarTodos() throws PersistenciaException {
        ArrayList<LocatarioDTO> locatario = null;

        locatario = dao.listarTodos();

        return locatario;
    }
}
