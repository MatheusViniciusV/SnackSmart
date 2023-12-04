package br.cefetmg.snacksmart.controller.locatario;

import br.cefetmg.snacksmart.dto.FornecedorDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.dto.LoteDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.facade.GestaoFornecedores;
import br.cefetmg.snacksmart.services.locatario.AcessarFornecedores;
import br.cefetmg.snacksmart.services.locatario.AcessarLotes;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
@WebServlet(name = "GerenciarEstoque", urlPatterns = {"/GerenciarEstoque"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1024,
        maxFileSize = 1024 * 1024 * 25,
        maxRequestSize = 1024 * 1024 * 125)

public class GerenciarEstoque extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AcessarLotes acessarLotes = new AcessarLotes();
        AcessarFornecedores acessarFornecedores = new AcessarFornecedores();
        
        String form = request.getParameter("novoLote");
        String remover = request.getParameter("remover");
        String atualizar = request.getParameter("atualizar");
        
         if ("novoLote".equals(form)){
            String tipoProduto = request.getParameter("tipoProduto");
            String quantidade = request.getParameter("quantidade");
            String precoCompra = request.getParameter("precoCompra");
            String precoVenda = request.getParameter("precoVenda");   
            String fornecedorId = request.getParameter("fornecedor");  
            Part imagemPart = request.getPart("imagem"); 
            InputStream imagemBytes = imagemPart.getInputStream();
            HttpSession session = request.getSession();
            LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");
            try {
                FornecedorDTO fornecedor = acessarFornecedores.getFornecedor(Integer.parseInt(fornecedorId));
                LoteDTO loteDTO = new LoteDTO(tipoProduto, Integer.parseInt(quantidade), Double.parseDouble(precoCompra), 
                Double.parseDouble(precoVenda), fornecedor, locatario, imagemBytes); 
                acessarLotes.adicionarNovoLote(loteDTO);
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarEstoque.class.getName()).log(Level.SEVERE, null, ex);
            }              
        }else if ("remover".equals(remover)){ 
            String loteId = request.getParameter("loteId");  
            try {
                acessarLotes.removerLote(Integer.parseInt(loteId));
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarEstoque.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("atualizar".equals(atualizar)){ 
            String tipoProduto = request.getParameter("novo-tipoProduto");
            String quantidade = request.getParameter("novo-quantidade");
            String precoCompra = request.getParameter("novo-precoCompra");
            String precoVenda = request.getParameter("novo-precoVenda");   
            String fornecedorId = request.getParameter("novo-fornecedor");  
            Part imagemPart = request.getPart("novo-imagem"); 
            InputStream imagemBytes = imagemPart.getInputStream();
            HttpSession session = request.getSession();
            LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");
            try {
                FornecedorDTO fornecedor = acessarFornecedores.getFornecedor(Integer.parseInt(fornecedorId));
                LoteDTO loteDTO = new LoteDTO(tipoProduto, Integer.parseInt(quantidade), Double.parseDouble(precoCompra), 
                Double.parseDouble(precoVenda), fornecedor, locatario, imagemBytes); 
                acessarLotes.atualizarLote(loteDTO);
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarEstoque.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        HttpSession session = request.getSession();
        ArrayList<FornecedorDTO> vetorFornecedorSQL = null;
        ArrayList<LoteDTO> vetorLoteSQL = null;
        
        AcessarFornecedores acessoFornecedores = new AcessarFornecedores();
        LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");
        
        try {                 
             vetorFornecedorSQL = acessoFornecedores.getAllFornecedores(locatario.getCPF());
             vetorLoteSQL = acessarLotes.recuperarLotesLocatario(locatario.getId());
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
