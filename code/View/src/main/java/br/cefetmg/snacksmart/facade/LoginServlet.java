package br.cefetmg.snacksmart.facade;

import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.ValidadorUsuario;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ValidadorUsuario validador = new ValidadorUsuario();
            
            String cpf = request.getParameter("usuario");
            String senha = request.getParameter("senha");
            
            TipoUsuario tipoUsuario = validador.tipoUsuario(cpf);
            if(tipoUsuario == TipoUsuario.NAO_CADASTRADO)
                response.sendRedirect("index.html");
            
            
            HttpSession session = request.getSession();
            
            switch(tipoUsuario) {
                case LOCADOR: // gerente
                    if(validador.validarGerente(cpf, senha)) {
                        response.sendRedirect("principal.jsp");
                        session.setAttribute("tipoUsuario", tipoUsuario);
                        session.setAttribute("usuario", validador.getGenrente());
                    } else {
                        response.sendRedirect("index.html");
                    }
                    break;
                case LOCATARIO: // locatário
                    if(validador.validarLocatario(cpf, senha)) {
                        response.sendRedirect("principal.jsp");
                        session.setAttribute("tipoUsuario", tipoUsuario);
                        session.setAttribute("usuario", validador.getLocatario(cpf));
                    } else {
                        response.sendRedirect("index.html");
                    }
                    break;
            }

            session.setAttribute("LOCATARIO", TipoUsuario.LOCATARIO);
            session.setAttribute("LOCADOR", TipoUsuario.LOCADOR);
            session.setMaxInactiveInterval(15000);
        } catch (PersistenciaException | UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        
        if(sessao != null) 
            sessao.invalidate();
        
        response.sendRedirect("index.html");
    }
}
