/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.VistoriaDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author VictorN77
 */
public interface IVistoriaDAO {
 
    ArrayList<VistoriaDTO> listarTodas() throws PersistenciaException;
    VistoriaDTO registraVistoria(VistoriaDTO vistoria)throws SQLException;
}
