/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public interface IAluguelDAO {
    Double getAluguel(int codigo) throws PersistenciaException;
    ArrayList<Double> getAlugueis(int locatarioId) throws PersistenciaException;
}
