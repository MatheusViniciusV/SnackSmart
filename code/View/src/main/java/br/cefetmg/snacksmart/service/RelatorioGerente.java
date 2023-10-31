/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.cefetmg.snacksmart.service;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

import br.cefetmg.snacksmart.dao.MaquinaDAO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import java.util.ArrayList;
/**
 *
 * @author marco
 */
@WebServlet(name = "RelatorioGerente", urlPatterns = {"/RelatorioGerente"})
public class RelatorioGerente extends HttpServlet {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            MaquinaDAO maquina = new MaquinaDAO();
            ArrayList<MaquinaDTO> listMAquinas = maquina.listarTodos();
            if (listMAquinas != null) {
                request.setAttribute("listMaquinas", listMAquinas);
                jsp = "/RelatorioGerente.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
