/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package br.cefetmg.snacksmart.controller_locatario;

import java.io.IOException;
import java.io.PrintWriter;

import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import br.cefetmg.snacksmart.exceptions.dao.ElementoNaoExisteException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.services.locatario.AcessarContratos;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eloym
 */
@WebServlet(name="SolicitarCancelarContrato", urlPatterns={"/SolicitarCancelarContrato"})
public class SolicitarCancelarContrato extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        int contratoId =  Integer.parseInt(request.getParameter("id"));
        String locatarioCPF = request.getParameter("locatarioCPF");
        AcessarContratos acessoContratos = null;
        try {
            acessoContratos = new AcessarContratos(locatarioCPF);
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }

//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "o locatario não tem acesso ao contrato" + contratoId + "."); 
        try {
            acessoContratos.solicitarCancelamento(contratoId);
            
            response.setContentType("text/plain");
            out.printf("cancelamento do contrato %d solicitada.", contratoId);     
        } catch(SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "erro interno.");
        } catch(ElementoNaoExisteException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "o contrato não existe.");            
        } catch(LocatarioInvalidoException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SolicitarCancelarContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
