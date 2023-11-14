package br.cefetmg.snacksmart.controller_locador;

import java.io.IOException;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.dao.GerenteDAO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AlterarDadosGerente", urlPatterns = {"/AlterarDadosGerente"})
public class AlterarDadosGerente extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            String rg = request.getParameter("rg");
            String email = request.getParameter("email");
            String telefone = request.getParameter("telefone");
            
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte hash[] = algorithm.digest(senha.getBytes("UTF-8"));
            
            StringBuilder aux = new StringBuilder();
            for (byte b : hash) {
                aux.append(String.format("%02X", 0xFF & b));
            }
            
            senha = aux.toString();
            
            GerenteDTO gerenteDTO = new GerenteDTO(nome, cpf, senha, rg, email, telefone);
            IGerenteDAO gerenteDAO = new GerenteDAO();
            
            try {
                gerenteDAO.set(gerenteDTO);
                response.sendRedirect("MeusDadosGerente.jsp");
            } catch(PersistenciaException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } catch(NoSuchAlgorithmException ex) {
            Logger.getLogger(AlterarDadosGerente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
