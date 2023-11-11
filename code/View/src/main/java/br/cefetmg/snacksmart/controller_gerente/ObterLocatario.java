package br.cefetmg.snacksmart.controller_gerente;

import java.io.IOException;
import java.io.PrintWriter;

import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.gerente.ManterLocatarios;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.google.gson.Gson;

@WebServlet(name="obterLocatario", urlPatterns={"/obterLocatario"})
public class ObterLocatario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

        if(tipoUsuario == TipoUsuario.LOCADOR) {
            String locatarioCpf = request.getParameter("locatarioCpf");

            Gson gson = new Gson();
            ManterLocatarios acesso = new ManterLocatarios();
            LocatarioDTO locatario = null;
            try {
                locatario = acesso.buscaPorCpf(locatarioCpf);
            } catch (PersistenciaException e) {
                throw new RuntimeException(e);
            }
            String jsonResposta = "";

            if(locatario != null) {
                jsonResposta = gson.toJson(locatario);
            }

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonResposta);
        }

    }
}
