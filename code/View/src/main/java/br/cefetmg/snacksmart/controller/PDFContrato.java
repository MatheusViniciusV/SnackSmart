package br.cefetmg.snacksmart.controller;

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
import java.util.logging.Level;
import java.util.logging.Logger;


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
//        long contratoId =  Long.parseLong(request.getParameter("id"));
//        String locatarioCPF = request.getParameter("locatarioCPF");
        String locatarioCPF = "000.000.000-00";
        long contratoId =  1;
        
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=contrato-" + request.getParameter("contratoId") + ".pdf");
            acessoContratos.getPdf(contratoId, locatarioCPF, response.getOutputStream());
        } catch(ElementoNaoExisteException | LocatarioInvalidoException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch(DocumentException | IOException | SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro Interno.\n" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PDFContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
