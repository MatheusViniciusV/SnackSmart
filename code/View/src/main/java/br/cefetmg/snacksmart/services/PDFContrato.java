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
import com.aspose.pdf.Position;
import com.aspose.pdf.TextFragment;
import java.io.OutputStream;
import java.time.LocalDate;


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
        
        LocalDate dataInicio = LocalDate.of(2023, 7, 1);
        LocalDate dataExpiracao = dataInicio.plusYears(1);  // Um ano após o início
        LocalDate dataPagamento = LocalDate.of(2023, 7, 1);
        String observacoes = "Observações aleatórias";  // Substitua por observações aleatórias

        ContratoDTO contrato = new ContratoDTO(dataInicio, dataExpiracao, dataPagamento, observacoes);

        
        Document documento = new Document();
        Page pagina = documento.getPages().add();
        TextFragment titulo = new TextFragment("Hello World!");
        titulo.setPosition(new Position(100, 900));
        titulo.getTextState().setFontSize(16);
        
        
        pagina.getParagraphs().add(titulo);
        
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
