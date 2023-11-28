package br.cefetmg.snacksmart.controller.locatario;

import br.cefetmg.snacksmart.dto.FornecedorDTO;
import br.cefetmg.snacksmart.dto.LoteDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.locatario.AcessarFornecedores;
import br.cefetmg.snacksmart.services.locatario.AcessarLotes;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
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
        String remocao = request.getParameter("remocao");
        
         if ("novoLote".equals(form)){
            String tipoProduto = request.getParameter("tipoProduto");
            String quantidade = request.getParameter("quantidade");
            String precoCompra = request.getParameter("precoCompra");
            String precoVenda = request.getParameter("precoVenda");   
            String fornecedorCPF = request.getParameter("fornecedor");  
            Part imagemPart = request.getPart("imagem"); //muita coisa pra consertar
            //FornecedorDTO fornecedor = acessarFornecedores.getAllFornecedores(fornecedorCPF);
            /*InputStream imagemBytes = imagemPart.getInputStream();
            LoteDTO loteDTO = new LoteDTO(tipoProduto, Integer.parseInt(quantidade), Double.parseDouble(precoCompra), Double.parseDouble(precoVenda),
                      , locatario[0], imagemBytes);
            try {
                acessarLotes.adicionarNovoLote(loteDTO);
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarEstoque.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }else if ("solicitado".equals(remocao)){ 
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String locatarioCPF = request.getParameter("locatario");             
         }      
    }
}
