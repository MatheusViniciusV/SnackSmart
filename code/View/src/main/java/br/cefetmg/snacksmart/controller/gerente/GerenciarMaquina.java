package br.cefetmg.snacksmart.controller.gerente;

import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.cefetmg.snacksmart.services.gerente.AcessarMaquinas;
import br.cefetmg.snacksmart.dao.LocatarioDAO;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name="GerenciarMaquina", urlPatterns={"/GerenciarMaquina"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,  
                 maxFileSize = 1024 * 1024 * 5,     
                 maxRequestSize = 1024 * 1024 * 5 * 5)  
public class GerenciarMaquina extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formulario = request.getParameter("formSelecionado");
        AcessarMaquinas acesso = new AcessarMaquinas();
        
        if (formulario != null) switch (formulario) {
            case "formAddMaquina":{
                String nome = request.getParameter("nome");
                String tipo = request.getParameter("tipo");
                String locatario = request.getParameter("locatario");
                String localizacao = request.getParameter("localizacao");
                Part imagemPart = request.getPart("imagem");
                byte[] imagemBytes = imagemPart.getInputStream().readAllBytes();
            try {
                acesso.formAddMaquina(nome, tipo, locatario, localizacao, imagemBytes);
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
                    break;
                }
            case "remocaoMaquina":{               
                String codigo = request.getParameter("removerMaquinaCodigo");
            try {
                acesso.remocaoMaquina( Integer.parseInt(codigo));
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
                    break;
                }
            case "formAtualizarMaquina":{
                String codigo = request.getParameter("atualizarMaquinaCodigo");
                String novoNome = request.getParameter("novoNome");
                String novoTipo = request.getParameter("novoTipo");
                String novoLocatario = request.getParameter("novoLocatario");
                String novaLocalizacao = request.getParameter("novaLocalizacao");        
                Part imagemPart = request.getPart("novaImagem");
                byte[] novaImagemBytes = imagemPart.getInputStream().readAllBytes();
            try {
                acesso.formAtualizarMaquina(Integer.parseInt(codigo), novoNome, novoTipo, novoLocatario, novaLocalizacao, novaImagemBytes);
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
                    break;
                }
            default:               
                break;
        }      
        
        LocatarioDAO locatarioDAO = new LocatarioDAO();
        try {
            request.setAttribute("listaLocatarios", locatarioDAO.listarTodos()); //Isso deve estar incorreto no modelo MVC por enquanto               
        } catch (PersistenciaException ex) {
            Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            request.setAttribute("vetorMaquinas", acesso.getAllMaquinas());
        } catch (PersistenciaException e) {
            try {
                throw new PersistenciaException(e.getMessage(), e);
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.getRequestDispatcher("WEB-INF/paginas/gestaoMaquina.jsp").forward(request, response);                
    }
}