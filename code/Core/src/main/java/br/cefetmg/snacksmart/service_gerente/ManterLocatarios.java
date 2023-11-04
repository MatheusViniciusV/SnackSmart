package br.cefetmg.snacksmart.service_gerente;

import br.cefetmg.snacksmart.dao.LocatarioDAO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;

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
}
