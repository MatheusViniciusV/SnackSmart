
package br.cefetmg.snacksmart.controller_gerente;

import java.io.IOException;

import br.cefetmg.snacksmart.controller_gerente.RelatorioGerente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author marco
 */
@WebServlet(name = "controler", urlPatterns = {"/controler"})
public class controler extends HttpServlet {
    private String jsp = "";
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String pagina = request.getParameter("pagina");
        
        if(pagina.equals("EmitirRelatorio"))
            jsp = RelatorioGerente.execute(request);
        
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }
    
}
