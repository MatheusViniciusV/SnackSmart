/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package br.cefetmg.snacksmart.controller_locatario;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import br.cefetmg.snacksmart.exceptions.dao.ElementoNaoExisteException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.service_locatario.AcessarContratos;
import java.sql.SQLException;

/**
 *
 * @author eloym
 */
@WebServlet(name="SolicitarCancelarContrato", urlPatterns={"/SolicitarCancelarContrato"})
public class SolicitarCancelarContrato extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        AcessarContratos acessoContratos = new AcessarContratos();
        
        long contratoId =  Long.parseLong(request.getParameter("contratoId"));
        String locatarioCPF = request.getParameter("locatarioCPF");
        
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "o locatario não tem acesso ao contrato" + contratoId + "."); 
        try {
            acessoContratos.solicitarCancelamento(contratoId, locatarioCPF);
            
            response.setContentType("text/plain");
            out.printf("cancelamento do contrato %d solicitada.", contratoId);     
        } catch(SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "erro interno.");
        } catch(ElementoNaoExisteException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "o contrato não existe.");            
        } catch(LocatarioInvalidoException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
