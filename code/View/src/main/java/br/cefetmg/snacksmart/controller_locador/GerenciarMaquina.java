package br.cefetmg.snacksmart.controller_locador;

import br.cefetmg.snacksmart.facade.GestaoMaquina;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.cefetmg.snacksmart.service_gerente.AcessarMaquinas;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.sql.SQLException;
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
                acesso.formAddMaquina(nome, tipo, locatario, localizacao, imagemBytes);
                    break;
                }
            case "remocaoMaquina":{               
                String codigo = request.getParameter("removerMaquinaCodigo");
                acesso.remocaoMaquina( Integer.parseInt(codigo));
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
                acesso.formAtualizarMaquina(Integer.parseInt(codigo), novoNome, novoTipo, novoLocatario, novaLocalizacao, novaImagemBytes);
                    break;
                }
            default:               
                break;
        }         
            try
            {
                request.setAttribute("vetorMaquinas", acesso.getAllMaquinas());
                request.getRequestDispatcher("WEB-INF/paginas/gestaoMaquina.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GestaoMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}