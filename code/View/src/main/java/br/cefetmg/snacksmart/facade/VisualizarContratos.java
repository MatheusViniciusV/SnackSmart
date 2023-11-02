package br.cefetmg.snacksmart.facade;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.service_gerente.ManterContratos;
import br.cefetmg.snacksmart.service_locatario.AcessarContratos;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eloym
 */
@WebServlet(name="VisualizarContratos", urlPatterns={"/visualizarContratos"})
public class VisualizarContratos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
        
        ArrayList<ContratoDTO> contratos = null;
        if(tipoUsuario == TipoUsuario.LOCATARIO) {
            LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");
            
            AcessarContratos acesso = new AcessarContratos();
            
            try {
                contratos = acesso.getContratos(locatario.getCPF());
            } catch (LocatarioInvalidoException | SQLException ex) {
                Logger.getLogger(VisualizarContratos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(tipoUsuario == TipoUsuario.LOCADOR) {
            ManterContratos acesso = new ManterContratos();
            
            try {
                contratos = acesso.getContratos();
            } catch (LocatarioInvalidoException | SQLException ex) {
                Logger.getLogger(VisualizarContratos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println(contratos.size());
        request.setAttribute("contratos", contratos);
        request.getRequestDispatcher("WEB-INF/paginas/visualizarContratos.jsp").forward(request, response);
    } 
}
