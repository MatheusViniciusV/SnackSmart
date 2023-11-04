/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package br.cefetmg.snacksmart.controller_locador;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.service_gerente.ManterContratos;
import br.cefetmg.snacksmart.service_gerente.ManterGerente;
import br.cefetmg.snacksmart.service_gerente.ManterLocatarios;
import br.cefetmg.snacksmart.utils.DataManager;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author eloym
 */
@WebServlet(name="CriarContrato", urlPatterns={"/CriarContrato"})
public class CriarContrato extends HttpServlet {
   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        ManterContratos service = new ManterContratos();
        ManterLocatarios acessoLocatarios = new ManterLocatarios();
        ManterGerente acessoGerente = new ManterGerente();

        String locatarioCPF = request.getParameter("cpfLocatario");
        DataManager dataInicio = new DataManager(request.getParameter("dataInicio"));
        DataManager dataFim = new DataManager(request.getParameter("dataFim"));
        DataManager dataPagamento = null;
        int diaPagagento = Integer.parseInt(request.getParameter("diaPagamento"));
        String observacoes  = request.getParameter("observacoes");
        double valor = Double.parseDouble(request.getParameter("valor"));

        if(diaPagagento > 0 && diaPagagento <= 29) {
            dataPagamento = new DataManager(LocalDate.of( dataInicio.getAno(), dataInicio.getMes(), diaPagagento));
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "data de pagamento invalida");
            return;
        }

        try {
            LocatarioDTO locatario = acessoLocatarios.buscaPorCpf(locatarioCPF);
            GerenteDTO gerente = acessoGerente.obterGerente();

            ContratoDTO contrato = new ContratoDTO(valor,
                    gerente,
                    locatario,
                    null,
                    dataInicio, dataFim, dataPagamento,
                    observacoes);

            ContratoDTO contratoCriado = service.criarContrato(contrato);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            out.printf(contratoCriado.toJson());

        } catch (PersistenciaException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "erro ao criar contrato");
        }
    }
}
