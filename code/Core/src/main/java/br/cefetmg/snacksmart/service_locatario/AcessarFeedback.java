package br.cefetmg.snacksmart.service_locatario;

import br.cefetmg.snacksmart.dao.FeedbackDAO;
import br.cefetmg.snacksmart.dto.FeedbackDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.IFeedbackDAO;
import br.cefetmg.snacksmart.utils.enums.TiposFeedback;
import java.util.ArrayList;

public class AcessarFeedback {  
    private final IFeedbackDAO feedbackDAO;
    public AcessarFeedback() {
        feedbackDAO = new FeedbackDAO();
    }
    
    public ArrayList<FeedbackDTO> getAllFeedback() throws PersistenciaException{
        ArrayList maquinas = feedbackDAO.getAll();
        return maquinas;
    }
    
    public FeedbackDTO getFeedback(int codigo) throws PersistenciaException{
        return feedbackDAO.get(codigo);
    }
    
    public void adicionarFeedback(int codigo, String titulo, String mensagem, String solicitacaoStr) throws PersistenciaException{
        TiposFeedback solicitacao = TiposFeedback.fromString(solicitacaoStr);
        FeedbackDTO feedbackDTO = new FeedbackDTO(codigo, titulo, mensagem, solicitacao);
        feedbackDAO.set(feedbackDTO);
    }
}
 
    
  
    

