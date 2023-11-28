package br.cefetmg.snacksmart.facade;

import br.cefetmg.snacksmart.dto.FeedbackDTO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
<<<<<<< HEAD:code/View/src/main/java/br/cefetmg/snacksmart/facade/RelatorioGerente.java
import java.io.IOException;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.locatario.AcessarFeedback;
import br.cefetmg.snacksmart.services.gerente.AcessarMaquinas;
=======
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.gerente.AcessarMaquinas;
import br.cefetmg.snacksmart.services.locatario.AcessarFeedback;
import java.io.IOException;
>>>>>>> 017ebea99d28d781aa0505a57e9a5b770df1fd8a:code/View/src/main/java/br/cefetmg/snacksmart/facade/EmissaoRelatorio.java
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
/**

/**
 *
 * @author marco
 */
@WebServlet(name = "EmissaoRelatorio", urlPatterns = {"/EmissaoRelatorio"})
public class EmissaoRelatorio extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        AcessarMaquinas acessoMaquina = new AcessarMaquinas();
        AcessarFeedback acessoFeedback = new AcessarFeedback();
        
        ArrayList<MaquinaDTO> vetorMaquinasSQL = null;
        ArrayList<MaquinaDTO> vetorMaquinas = new ArrayList<>();
        ArrayList<FeedbackDTO> vetorFeedbacks = null;
         try {
             vetorMaquinasSQL = acessoMaquina.getAllMaquinasGerente();
             vetorFeedbacks = acessoFeedback.getAllFeedback();
             
              externo: for(MaquinaDTO maquina: vetorMaquinasSQL){
                 for(FeedbackDTO feedback: vetorFeedbacks){
                        if(maquina.getCodigo() == feedback.getCodigo() ){
                            vetorMaquinas.add(maquina);
                            break externo;
                     }
                 }
             }
         } catch (PersistenciaException ex) {
             Logger.getLogger(ManutecaoVistorias.class.getName()).log(Level.SEVERE, null, ex);
         }
         for(MaquinaDTO maquina: vetorMaquinas){
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
        request.setAttribute("vetorMaquinas", vetorMaquinas);
        request.setAttribute("vetorFeedbacks", vetorFeedbacks);
        request.getRequestDispatcher("WEB-INF/paginas/emissaoRelatorio.jsp").forward(request, response);
    }
}