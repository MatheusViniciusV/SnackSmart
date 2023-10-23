/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package br.cefetmg.snacksmart.services;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.idao.IContratosDAO;
import br.cefetmg.snacksmart.dao.ContratoDAO;
import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.TextFragment;
import java.io.OutputStream;


/**
 *
 * @author eloym
 */
@WebServlet(name="PDFContrato", urlPatterns={"/PDFContrato"})
public class PDFContrato extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("application/pdf");
        

        
        Document documento = new Document();
        Page pagina = documento.getPages().add();
        pagina.getParagraphs().add(new TextFragment("Hello World!"));
        
        // Configurar cabeçalhos para download
        response.setHeader("Content-Disposition", "attachment; filename=\"contrato-.pdf\"");

        OutputStream out = response.getOutputStream();


        // Salve o documento PDF no OutputStream
        documento.save(out);

        // Feche o documento
        documento.close();
    } 

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
