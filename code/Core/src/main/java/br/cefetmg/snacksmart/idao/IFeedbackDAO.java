/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.FeedbackDTO;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public interface IFeedbackDAO {
    FeedbackDTO get(int codigo);
    ArrayList<FeedbackDTO> getAll();
    void set(FeedbackDTO feedback);
}
