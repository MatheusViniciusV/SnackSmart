package br.cefetmg.snacksmart.facade;


import br.cefetmg.snacksmart.services.locatario.AcessarFornecedores;
import br.cefetmg.snacksmart.dto.FornecedorDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.dto.LoteDTO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.locatario.AcessarLotes;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@author Arthur Milagres

@WebServlet(name = "GestaoLote", urlPatterns = {"/GestaoLote"})
public class GestaoLote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");       
        HttpSession session = request.getSession();
        ArrayList<FornecedorDTO> vetorFornecedorSQL = null;
        ArrayList<LoteDTO> vetorLoteSQL = null;
        
        AcessarFornecedores acessoFornecedores = new AcessarFornecedores();
        AcessarLotes acessarLotes = new AcessarLotes();
        LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");
        
        try {                 
             vetorFornecedorSQL = acessoFornecedores.getAllFornecedores(locatario.getCPF());
             vetorLoteSQL = acessarLotes.recuperarLotesLocatario(locatario.getCPF());
        } catch (PersistenciaException ex) {
            Logger.getLogger(GestaoFornecedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (vetorLoteSQL != null){
            for (LoteDTO lote : vetorLoteSQL){
                InputStream imagemStream = lote.getImagem();
                if (imagemStream != null){
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;

                    while ((length = imagemStream.read(buffer)) != -1) {
                        baos.write(buffer, 0, length);
                    }       
                    byte[] bytes = baos.toByteArray();
                    String base64String = java.util.Base64.getEncoder().encodeToString(bytes);
                    lote.setUrlImagem(base64String);
                } else {
                    lote.setUrlImagem("none");
                }
            }
        }
        
        request.setAttribute("vetorLotes", vetorLoteSQL);
        request.setAttribute("vetorFornecedores", vetorFornecedorSQL);
        request.setAttribute("usuarioResponsavel", locatario);       
        request.getRequestDispatcher("WEB-INF/paginas/gestaoEstoque.jsp").forward(request, response);
    }
}
