
package br.cefetmg.snacksmart.services_locatario;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.time.LocalDate;

/**
 *
 * @author eloym
 */
@WebServlet(name="SolicitarCancelarContrato", urlPatterns={"/SolicitarCancelarContrato"})
public class SolicitarCancelarContrato extends HttpServlet {

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String stringRecebida = request.getParameter("minhaString");

        // Verifique se a string é "oi"
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
            
            LocalDate dataInicio = LocalDate.of(2023, 1, 1); // 1 de janeiro de 2023
            LocalDate dataExpiracao = LocalDate.of(2023, 12, 31); // 31 de dezembro de 2023
            LocalDate dataPagamento = LocalDate.of(2023, 5, 15); // 15 de maio de 2023

            // Gere um ID aleatório e uma observação aleatória
            long id = 12345;  // Substitua por um valor aleatório
            String observacoes = "Observações aleatórias";  // Substitua por observações aleatórias

            // Crie uma instância de ContratoDTO com os dados aleatórios
            ContratoDTO contrato;
            contrato = new ContratoDTO(dataInicio, dataExpiracao, dataPagamento, observacoes);
            
            out.write("CU");
        
    }
    
}
