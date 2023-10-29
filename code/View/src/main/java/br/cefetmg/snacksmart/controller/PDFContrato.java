package br.cefetmg.snacksmart.services;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import br.cefetmg.snacksmart.exceptions.dao.ElementoNaoExisteException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.service_locatario.AcessarContratos;
import com.itextpdf.text.DocumentException;
import java.sql.SQLException;


/**
 *
 * @author eloym
 */
@WebServlet(name="PDFContrato", urlPatterns={"/PDFContrato"})
public class PDFContrato extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        AcessarContratos acessoContratos = new AcessarContratos();
        long contratoId =  Long.parseLong(request.getParameter("contratoId"));
        String locatarioCPF = request.getParameter("locatarioCPF");
        
        try {
            acessoContratos.getPdf(contratoId, locatarioCPF, response.getOutputStream());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=contrato-" + request.getParameter("contratoId") + ".pdf");
        } catch(ElementoNaoExisteException | LocatarioInvalidoException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch(DocumentException | IOException | SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro Interno.\n" + e.getMessage());
        }
        
    }
}
