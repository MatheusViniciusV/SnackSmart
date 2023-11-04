package br.cefetmg.snacksmart.facade;

import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.service_gerente.AcessarMaquinas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "GestaoMaquina", urlPatterns = {"/gestaoMaquina"})
public class GestaoMaquina extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        AcessarMaquinas acesso = new AcessarMaquinas();
        ArrayList<MaquinaDTO> vetorMaquinasSQL = acesso.getAllMaquinas();
        request.setAttribute("vetorMaquinas", vetorMaquinasSQL);
        request.getRequestDispatcher("WEB-INF/paginas/gestaoMaquina.jsp").forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        AcessarMaquinas acesso = new AcessarMaquinas();
        ArrayList<MaquinaDTO> vetorMaquinasSQL = null;
        try {
            vetorMaquinasSQL = acesso.getAllMaquinas();
        } catch (SQLException ex) {
            Logger.getLogger(GestaoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("vetorMaquinas", vetorMaquinasSQL);
        request.getRequestDispatcher("WEB-INF/paginas/gestaoMaquina.jsp").forward(request, response);
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
