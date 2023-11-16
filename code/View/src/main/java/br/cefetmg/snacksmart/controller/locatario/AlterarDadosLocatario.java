/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.cefetmg.snacksmart.controller.locatario;

import java.io.IOException;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.ILocatarioDAO;
import br.cefetmg.snacksmart.dao.LocatarioDAO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AlterarDadosLocatario", urlPatterns = {"/AlterarDadosLocatario"})
public class AlterarDadosLocatario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
            
        HttpSession session = request.getSession();
        LocatarioDTO locatarioDTO = (LocatarioDTO) session.getAttribute("usuario");
            
        locatarioDTO.setNome(request.getParameter("nome"));
        locatarioDTO.setRG(request.getParameter("rg"));
        locatarioDTO.setEmail(request.getParameter("email"));
        locatarioDTO.setTelefone(request.getParameter("telefone"));
    
        ILocatarioDAO LocatarioDAO = new LocatarioDAO();
            
        try {
            LocatarioDAO.atualizar(locatarioDTO);
            response.sendRedirect("MeusDadosLocatario.jsp");
        } catch(PersistenciaException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}