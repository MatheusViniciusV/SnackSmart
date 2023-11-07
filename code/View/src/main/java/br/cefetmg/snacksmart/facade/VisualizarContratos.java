package br.cefetmg.snacksmart.facade;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.service_gerente.ManterContratos;
import br.cefetmg.snacksmart.service_gerente.ManterLocatarios;
import br.cefetmg.snacksmart.service_locatario.AcessarContratos;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;
import br.cefetmg.snacksmart.utils.enums.TipoMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import java.io.IOException;

import br.cefetmg.snacksmart.utils.enums.TiposOrdenacaoContrato;
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

        if(request.getParameterMap().isEmpty()) {
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
                    contratos = acesso.filtraContratos(StatusContrato.VIGENTE, TiposOrdenacaoContrato.MENOR_ID);
                } catch (LocatarioInvalidoException | SQLException ex) {
                    Logger.getLogger(VisualizarContratos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            StatusContrato status = StatusContrato.valueOf(request.getParameter("status"));
            TiposOrdenacaoContrato ordenacao = TiposOrdenacaoContrato.valueOf(request.getParameter("ordenacao"));
            ManterContratos acesso = new ManterContratos();
            

            if(tipoUsuario == TipoUsuario.LOCADOR) {
                String locatarioCpf = request.getParameter("cpf");
                
                ManterLocatarios acessoLocatarios = new ManterLocatarios();
                
                try {
                    LocatarioDTO locatario = acessoLocatarios.buscaPorCpf(locatarioCpf);
                    contratos = acesso.filtraContratos(locatario, status, ordenacao);
                } catch (LocatarioInvalidoException | SQLException | PersistenciaException ex) {
                    Logger.getLogger(VisualizarContratos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(tipoUsuario == TipoUsuario.LOCATARIO) {
                LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");
                
                try {
                    contratos = acesso.filtraContratos(locatario, status, ordenacao);
                } catch (LocatarioInvalidoException ex) {
                    Logger.getLogger(VisualizarContratos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(VisualizarContratos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        request.setAttribute("tipoMaquina", TipoMaquina.values());
        request.setAttribute("tipoStatus", StatusContrato.values());
        request.setAttribute("tipoOrdenacao", TiposOrdenacaoContrato.values());
        assert contratos != null;
        request.setAttribute("contratos", contratos);
        request.getRequestDispatcher("WEB-INF/paginas/visualizarContratos.jsp").forward(request, response);
    } 
}
