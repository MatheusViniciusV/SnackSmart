
package br.cefetmg.snacksmart.facade;

import br.cefetmg.snacksmart.dto.FeedbackDTO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.services.gerente.AcessarMaquinas;
import br.cefetmg.snacksmart.services.locatario.AcessarFeedback;
import java.io.IOException;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
/**
 *
 * @author marco
 */
@WebServlet(name = "ManutecaoVistorias", urlPatterns = {"/manutecaoVistorias"})
public class ManutecaoVistorias extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        AcessarMaquinas acessoMaquinas = new AcessarMaquinas();
        AcessarFeedback acessoFeedback = new AcessarFeedback();
        
        ArrayList<MaquinaDTO> vetorMaquinasSQL = null;
        ArrayList<FeedbackDTO> vetorFeedbackSQL = null;
        ArrayList<FeedbackDTO> vetorFeedback = new ArrayList<>();
        try {
            vetorFeedbackSQL = acessoFeedback.getAllFeedback();
            vetorMaquinasSQL = acessoMaquinas.getAllMaquinasGerente();
            if (vetorFeedbackSQL != null){
                for(FeedbackDTO feedback: vetorFeedbackSQL){
                    if("ERRO".equals(feedback.getTipoFeedback().toString())){
                        vetorFeedback.add(feedback);
                    }
                }
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(ManutecaoVistorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (vetorMaquinasSQL != null){
            for(MaquinaDTO maquina: vetorMaquinasSQL){
                InputStream imagemStream = maquina.getImagem();
                if (imagemStream != null){
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;

                    while ((length = imagemStream.read(buffer)) != -1) {
                        baos.write(buffer, 0, length);
                    }       
                    byte[] bytes = baos.toByteArray();
                    String base64String = java.util.Base64.getEncoder().encodeToString(bytes);
                    maquina.setUrlImagem(base64String);
                } else {
                    maquina.setUrlImagem("none");
                }
            }
        }
        request.setAttribute("vetorFeedback", vetorFeedbackSQL);
        request.setAttribute("vetorFeedbackManutencao", vetorFeedback);
        request.setAttribute("vetorMaquinas", vetorMaquinasSQL);
        request.getRequestDispatcher("WEB-INF/paginas/manutencaoVistorias.jsp").forward(request, response);
    }
}