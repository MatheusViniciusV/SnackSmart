package br.cefetmg.snacksmart.facade;


import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.services.gerente.AcessarMaquinas;
import java.io.IOException;
import br.cefetmg.snacksmart.dao.LocatarioDAO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.locatario.AcessarAluguel;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
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
 *
 * @author marco
 */
@WebServlet(name = "GestaoFinanceiroLocatario", urlPatterns = {"/gestaoFinanceiroLocatario"})
public class GestaoFinanceiroLocatario extends HttpServlet {
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        AcessarAluguel acesso = new AcessarAluguel();
        
        ArrayList<Double> vetorAluguelSQL = null;
        HttpSession session = request.getSession();
        TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
        Double faturamento, gastos, saldo;
        faturamento = gastos = saldo = 0.0;
        
        LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");
      try {
          vetorAluguelSQL = acesso.getAllAluguel(locatario.getId());
      } catch (PersistenciaException ex) {
          Logger.getLogger(GestaoFinanceiroLocatario.class.getName()).log(Level.SEVERE, null, ex);
      }
        for(Double aluguel: vetorAluguelSQL){
            gastos+= aluguel;
        }
        saldo = faturamento - gastos;

        request.setAttribute("faturamento",faturamento);
        request.setAttribute("gastos", gastos);
        request.setAttribute("saldo", saldo);
        request.getRequestDispatcher("WEB-INF/paginas/gestaoFinanceirioLocatario.jsp").forward(request, response);
    }


}
