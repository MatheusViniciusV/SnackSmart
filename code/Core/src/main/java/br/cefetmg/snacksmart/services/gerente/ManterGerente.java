package br.cefetmg.snacksmart.services.gerente;

import br.cefetmg.snacksmart.dao.GerenteDAO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;

public class ManterGerente {
    private GerenteDAO dao;

    public ManterGerente() {
        dao = new GerenteDAO();
    }

    public GerenteDTO obterGerente() throws PersistenciaException {
        return dao.get();
    }

    public void atualizarGerente(GerenteDTO gerenteDTO) throws PersistenciaException {
        dao.set(gerenteDTO);
    }
}
