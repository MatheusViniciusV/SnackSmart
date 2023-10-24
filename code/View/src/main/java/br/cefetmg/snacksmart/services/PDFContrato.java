/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package br.cefetmg.snacksmart.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




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
        response.setHeader("Content-Disposition", "attachment; filename=teste.pdf");
        
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, response.getOutputStream());
            
            criaPDF(documento);
        } catch(DocumentException | IOException de) {
            System.err.println(de.getMessage());
        } finally {
            documento.close();
        }

    } 
    
    private void criaPDF(Document documento) throws DocumentException, IOException {
        float margensLaterais = 50;
        BaseFont fontBase = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        Font fontTitulo = new Font(fontBase, 20, Font.BOLD);
        
        documento.open();
        documento.setMargins(margensLaterais, margensLaterais, 10, 10);
        // adicionando um parágrafo ao documento
        
        documento.add(new Paragraph("Hello World", fontTitulo));
        documento.add(new Paragraph("Gerando um PDF usando iText em Java"));

        // adicionando um parágrafo com fonte diferente ao arquivo
        documento.add(new Paragraph("Adicionando outro paragrafo"));
        
        documento.add(new Paragraph("testando se rodou\nsla"));
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
