package br.cefetmg.snacksmart.controller_locador;

import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.service_gerente.AcessarMaquinas;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoMaquina;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="obterMaquinaDisponivel", urlPatterns={"/obterMaquinaDisponivel"})
public class ObterMaquinaDisponivel extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

        if(tipoUsuario == TipoUsuario.LOCADOR) {
            AcessarMaquinas acesso = new AcessarMaquinas();
            MaquinaDTO maquina = null;

            int codigo = Integer.parseInt(request.getParameter("codigoMaquina"));

            try {
                maquina = acesso.getMaquinaPorCodigoStatus(codigo, StatusMaquina.DISPONIVEL);
            } catch (PersistenciaException e) {
                throw new RuntimeException(e);
            }

            String jsonResposta = "";

            if(maquina != null) {
                jsonResposta = maquina.toJson();
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(jsonResposta);
            } else {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }

        }

    }
}
