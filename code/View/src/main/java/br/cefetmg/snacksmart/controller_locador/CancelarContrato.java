package br.cefetmg.snacksmart.controller_locador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import br.cefetmg.snacksmart.service_gerente.ManterContratos;

/**
 * @author eloym
 */
@WebServlet(name="CancelarContrato", urlPatterns={"/CancelarContrato"})
public class CancelarContrato extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ManterContratos acesso = new ManterContratos(); 
        
        int contratoId =  Integer.parseInt(request.getParameter("contratoId"));

        
        try {
            acesso.cancelarContrato(contratoId);

            response.setContentType("text/plain");
            out.printf("contrato %d cancelado.", contratoId);
            
        } catch(SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "erro interno.");
        } catch (ClassNotFoundException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "o contrato não existe.");
        }
    }
}
