/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.cefetmg.snacksmart.controller.gerente;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.cefetmg.snacksmart.services.gerente.ManterContratos;
import java.sql.SQLException;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;

/**
 *
 * @author VictorN77
 */
@WebServlet(name = "validarContrato", urlPatterns = {"/validarContrato"})
public class ValidarContrato extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        ManterContratos mContratos = new ManterContratos();
        int id =  Integer.parseInt(request.getParameter("id"));
        
        try{
        
            mContratos.validaContrato(id);
            
            response.setContentType("text/plain");
        }
         catch(SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "erro interno.");
        } catch (ClassNotFoundException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "o contrato não existe.");
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }

        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
