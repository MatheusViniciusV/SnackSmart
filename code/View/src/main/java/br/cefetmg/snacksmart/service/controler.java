
package br.cefetmg.snacksmart.service;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
