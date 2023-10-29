package br.cefetmg.snacksmart.controller_locador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import br.cefetmg.snacksmart.dao.ContratosDAO;
import br.cefetmg.snacksmart.idao.IContratosDAO;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;

/**
 * @author eloym
 */
@WebServlet(name="CancelarContrato", urlPatterns={"/CancelarContrato"})
public class CancelarContrato extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        IContratosDAO contratoDAO = new ContratosDAO(); 
        
        long contratoId =  Long.parseLong(request.getParameter("contratoId"));
        
        try {
            if(contratoDAO.getId(contratoId) != null) {
                contratoDAO.atualizarStatus(contratoId, StatusContrato.CANCELADO);

                response.setContentType("text/plain");
                out.printf("contrato %d cancelado.", contratoId);

            } else if(contratoDAO.getId(contratoId) != null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "o contrato não existe.");
            }
        } catch(SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "erro interno.");
        }
    }
}
