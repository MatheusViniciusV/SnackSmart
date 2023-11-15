package br.cefetmg.snacksmart.controller.gerente;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.cefetmg.snacksmart.dao.LocatarioDAO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "GestaoLocatarios", urlPatterns = {"/GestaoLocatarios"})
public class GestaoLocatarios extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            
            out.println("1");
            
            LocatarioDAO locatarioDAO = new LocatarioDAO();
            ArrayList<LocatarioDTO> locatarios;
            LocatarioDTO locatario;
            
            out.println("2");
            
            String submitType = request.getParameter("submitType");
            
            out.println("3");
            
            String senha;
            MessageDigest algorithm;
            StringBuilder aux;
            byte hash[];
            
            out.println("5");
            
            if (null != submitType) switch (submitType) {
                case "adicionar":
                    
                    out.println("6");
                    
                    locatario = new LocatarioDTO();
                    locatario.setNome(request.getParameter("nome"));
                    locatario.setCPF(request.getParameter("cpf"));
                    senha = request.getParameter("senha");
                    
                    out.println("7");
                    
                    algorithm = MessageDigest.getInstance("SHA-256");
                    hash = algorithm.digest(senha.getBytes("UTF-8"));
                    
                    out.println("8");

                    aux = new StringBuilder();
                    for (byte b : hash) {
                        aux.append(String.format("%02X", 0xFF & b));
                    }
                    
                    out.println("9");

                    senha = aux.toString();
                    
                    out.println("10");
                    
                    locatario.setSenha(senha);
                    locatario.setEmail(request.getParameter("email"));
                    locatario.setTelefone(request.getParameter("telefone"));
                    locatario.setRG(request.getParameter("rg"));
                    
                    out.println("11");
                    
                    locatarioDAO.inserir(locatario);
                    
                    out.println("12");
                    break;
                case "deletar":
                    locatarios = locatarioDAO.listarTodos();
                    locatario = locatarios.get(Integer.parseInt(request.getParameter("lista")));
                    locatarioDAO.delete(locatario);
                    break;
                case "atualizar":
                    locatarios = locatarioDAO.listarTodos();
                    locatario = locatarios.get(Integer.parseInt(request.getParameter("lista")));
                    locatario.setNome(request.getParameter("nome"));
                    locatario.setCPF(request.getParameter("cpf"));
                    senha = request.getParameter("senha");
                    
                    algorithm = MessageDigest.getInstance("SHA-256");
                    hash = algorithm.digest(senha.getBytes("UTF-8"));

                    aux = new StringBuilder();
                    for (byte b : hash) {
                        aux.append(String.format("%02X", 0xFF & b));
                    }

                    senha = aux.toString();
                    
                    locatario.setSenha(senha);
                    locatario.setEmail(request.getParameter("email"));
                    locatario.setTelefone(request.getParameter("telefone"));
                    locatario.setRG(request.getParameter("rg"));
                    
                    locatarioDAO.atualizar(locatario);
                    break;
                default:
            }
            out.println("13");
            response.sendRedirect("GestaoLocatarios.jsp");
        } catch (PersistenciaException ex) {
            Logger.getLogger(GestaoLocatarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GestaoLocatarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
