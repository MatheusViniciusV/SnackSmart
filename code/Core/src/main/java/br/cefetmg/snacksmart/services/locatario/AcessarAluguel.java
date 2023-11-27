
package br.cefetmg.snacksmart.services.locatario;

import br.cefetmg.snacksmart.dao.AluguelDAO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.IAluguelDAO;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class AcessarAluguel {
    private final IAluguelDAO aluguelDAO;
    public AcessarAluguel() {
        aluguelDAO = new AluguelDAO();
    }
    public Double getAluguel(int codigo) throws PersistenciaException{
        Double aluguel = aluguelDAO.getAluguel(codigo);
        return aluguel;
    }
    public ArrayList<Double> getAllAluguel(int locatarioId) throws PersistenciaException{
        ArrayList aluguel = aluguelDAO.getAlugueis(locatarioId);
        return aluguel;
    }
}
