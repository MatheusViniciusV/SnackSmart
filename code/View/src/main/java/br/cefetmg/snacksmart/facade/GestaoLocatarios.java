package br.cefetmg.snacksmart.facade;

import java.io.IOException;

import br.cefetmg.snacksmart.services.gerente.ManterLocatarios;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

@WebServlet(name = "gestaoLocatarios", urlPatterns = {"/gestaoLocatarios"})
public class GestaoLocatarios extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

        if(tipoUsuario != TipoUsuario.LOCADOR) {
            session.invalidate();
        } else {
            ManterLocatarios gestao = new ManterLocatarios();

            try {
                ArrayList<LocatarioDTO> locatarioDTOS = gestao.listaLocatarios();

                request.setAttribute("locatarios", locatarioDTOS);
                request.getRequestDispatcher("WEB-INF/paginas/gestaoLocatarios.jsp").forward(request, response);
            } catch (PersistenciaException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
