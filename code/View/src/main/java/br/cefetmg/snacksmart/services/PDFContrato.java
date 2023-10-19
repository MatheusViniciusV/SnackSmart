/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package br.cefetmg.snacksmart.services;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author eloym
 */
@WebServlet(name="PDFContrato", urlPatterns={"/PDFContrato"})
public class PDFContrato extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("application/pdf");

        // Configurar cabeçalhos para download
        response.setHeader("Content-Disposition", "attachment; filename=\"contrato-.pdf\"");

    } 

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
