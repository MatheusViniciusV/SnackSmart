/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.cefetmg.snacksmart.service;

import br.cefetmg.snacksmart.dao.ContratosDAO;
import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.dao.MaquinaDAO;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.*;
import java.util.ArrayList;

/**
 *
 * @author VictorN77
 */
@WebServlet(urlPatterns = {"/GerFinanceiro"})
public class GerFinanceiro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            double recebimento = 0.00, saldo, gasto = 0.00;

            ContratosDAO Contratos = new ContratosDAO();
            try {
                ArrayList<ContratoDTO> listaContratos = Contratos.listaTodos();

                for (ContratoDTO i : listaContratos) {

                    recebimento += i.getValorPagamento();

                }
            } catch (Exception a) {
            }
            MaquinaDAO Maquinas = new MaquinaDAO();
            
            try{
            
                ArrayList<MaquinaDTO> listaMaquinas = Maquinas.acessarTodasMaquinas();
                
                for (MaquinaDTO m : listaMaquinas){
                
                    //gasto += m.getValor();
                }
            }
            catch(Exception e){}
            

            saldo = recebimento - gasto;

            /* TODO output your page here. You may use following sample code. */
            String dados[] = new String[3];
            dados[0] = Double.toString(recebimento);
            dados[1] = Double.toString(gasto);
            dados[2] = Double.toString(saldo);            

            request.setAttribute("dados", dados);
            RequestDispatcher d = request.getRequestDispatcher("FinanceiroGerente.jsp");
            d.forward(request, response);

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
