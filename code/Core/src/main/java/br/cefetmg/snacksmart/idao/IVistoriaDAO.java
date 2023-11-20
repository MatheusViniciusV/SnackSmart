package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.VistoriaDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author VictorN77
 */
public interface IVistoriaDAO { 
    ArrayList<VistoriaDTO> listarTodas() throws PersistenciaException;
    void registraVistoria(VistoriaDTO vistoria)throws SQLException;
}
