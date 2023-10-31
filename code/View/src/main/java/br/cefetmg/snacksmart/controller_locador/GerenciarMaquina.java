package br.cefetmg.snacksmart.controller_locador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.cefetmg.snacksmart.service_gerente.AcessarMaquinas;
import jakarta.servlet.http.Part;

@WebServlet(name = "GerenciarMaquina", urlPatterns = {"/GerenciarMaquina"})
public class GerenciarMaquina extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formulario = request.getParameter("formulario");
        AcessarMaquinas acesso = new AcessarMaquinas();
        if (null != formulario) switch (formulario) {
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
                acesso.remocaoMaquina(codigo);
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
                acesso.formAtualizarMaquina(codigo, novoNome, novoTipo, novoLocatario, novaLocalizacao, novaImagemBytes);
                    break;
                }
            default:
                break;
        } 
    }
}