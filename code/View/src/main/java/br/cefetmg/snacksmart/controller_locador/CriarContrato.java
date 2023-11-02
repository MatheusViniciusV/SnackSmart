/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package br.cefetmg.snacksmart.controller_locador;

import java.io.IOException;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.service_gerente.ManterContratos;
import br.cefetmg.snacksmart.utils.DataManager;
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
        ManterContratos service = new ManterContratos();

        DataManager dataInicio = new DataManager(request.getParameter("dataInicio"));
        DataManager dataFim = new DataManager(request.getParameter("dataFim"));
        DataManager dataPagagento = new DataManager(request.getParameter("dataPagagento"));
        String observacoes  = request.getParameter("observações");
        String nomeLocatario = request.getParameter("nomeLocatario");
        String cpfLocatario = request.getParameter("cpfLocatario");
        String emailLocatario = request.getParameter("emailLocatario");
        String telLocatario = request.getParameter("telefoneLocatario");

        LocatarioDTO locatario = new LocatarioDTO(nomeLocatario, cpfLocatario, emailLocatario, telLocatario);
//        ContratoDTO contrato = new ContratoDTO(locatario, dataInicio.getData(), dataFim.getData(), dataPagagento.getData(), observacoes);

//        service.criarContrato(contrato);
    }
}
