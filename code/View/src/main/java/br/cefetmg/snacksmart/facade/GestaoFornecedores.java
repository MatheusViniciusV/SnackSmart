package br.cefetmg.snacksmart.facade;

import br.cefetmg.snacksmart.dto.FornecedorDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.locatario.AcessarFornecedores;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arthur Milagres
 */
@WebServlet(name = "GestaoFornecedores", urlPatterns = {"/gestaoFornecedores"})
public class GestaoFornecedores extends HttpServlet {  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<FornecedorDTO> vetorFornecedorSQL = null;
        HttpSession session = request.getSession();
        LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");
        AcessarFornecedores acessoFornecedores = new AcessarFornecedores();
        try {
            vetorFornecedorSQL = acessoFornecedores.getAllFornecedores(locatario.getCPF());
        } catch (PersistenciaException ex) {
            Logger.getLogger(GestaoFornecedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("usuarioResponsavel", locatario);
        request.setAttribute("vetorFornecedores", vetorFornecedorSQL);
        request.getRequestDispatcher("WEB-INF/paginas/gestaoFornecedor.jsp").forward(request, response);
    }
}
