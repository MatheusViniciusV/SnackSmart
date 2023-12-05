package br.cefetmg.snacksmart.controller.gerente;

import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.cefetmg.snacksmart.services.gerente.AcessarMaquinas;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.services.gerente.ManterLocatarios;
import br.cefetmg.snacksmart.services.gerente.ManterLocatarios;
import br.cefetmg.snacksmart.services.locatario.AcessarFeedback;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name="GerenciarMaquina", urlPatterns={"/GerenciarMaquina"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1024,
        maxFileSize = 1024 * 1024 * 25,
        maxRequestSize = 1024 * 1024 * 125)
public class GerenciarMaquina extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formulario = request.getParameter("formSelecionado");
        AcessarMaquinas acessoMaquina = new AcessarMaquinas();
        AcessarFeedback acessoFeedback = new AcessarFeedback();
        if (formulario != null) switch (formulario) {
            case "formAddMaquina":{
                String nome = request.getParameter("nome");
                String tipo = request.getParameter("tipo");
                String locatario = request.getParameter("locatario");
                String localizacao = request.getParameter("localizacao");
                Part imagemPart = request.getPart("imagem");
                InputStream imagemBytes = imagemPart.getInputStream();
                try {
                    acessoMaquina.formAddMaquina(nome, tipo, locatario, localizacao, imagemBytes);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "remocaoMaquina":{
                String codigo = request.getParameter("removerMaquinaCodigo");
                try {
                    acessoMaquina.remocaoMaquina( Integer.parseInt(codigo));
                } catch (PersistenciaException ex) {
                    Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "formAtualizarMaquina":{
                String codigo = request.getParameter("atualizarMaquinaCodigo");
                String novoNome = request.getParameter("novoNome");
                String novoLocatario = request.getParameter("novoLocatario");
                String novaLocalizacao = request.getParameter("novaLocalizacao");
                String novoStatus = request.getParameter("status");
                Part novaImagemPart = request.getPart("novaImagem");
                if (novaImagemPart != null && novaImagemPart.getSize() > 0) {
                    InputStream novaImagemBytes = novaImagemPart.getInputStream();
                    try {
                        acessoMaquina.formAtualizarMaquina(Integer.parseInt(codigo), novoNome, novaLocalizacao, novoLocatario, novoStatus, novaImagemBytes);
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else {
                    try {
                        acessoMaquina.formAtualizarMaquina(Integer.parseInt(codigo), novoNome, novaLocalizacao, novoLocatario, novoStatus, null);
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
            case "feedbackMaquina":{
                String codigo = request.getParameter("feedbackMaquinaCodigo");
                String titulo = request.getParameter("tituloFeedback");
                String mensagem = request.getParameter("mensagemFeedback");
                String solicitacao = request.getParameter("solicitarManutencao");
                if (solicitacao == null){
                    try {
                        acessoFeedback.adicionarFeedback(Integer.parseInt(codigo), titulo, mensagem, "COMENTARIO");
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    try {
                        acessoFeedback.adicionarFeedback(Integer.parseInt(codigo), titulo, mensagem, "ERRO");
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }

        ArrayList<MaquinaDTO> vetorMaquinasSQL = null;
         ManterLocatarios acessoLocatario = new ManterLocatarios();
        HttpSession session = request.getSession();
        TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

        if (tipoUsuario == TipoUsuario.LOCADOR){
            try {
                vetorMaquinasSQL =  acessoMaquina.getAllMaquinasGerente();
                request.setAttribute("listaLocatarios", acessoLocatario.listaLocatarios());
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");
            try {
                vetorMaquinasSQL = acessoMaquina.getAllMaquinasLocatario(locatario.getId());
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.setAttribute("usuarioAcessando", tipoUsuario);
        if (vetorMaquinasSQL != null){
            for (MaquinaDTO maquina : vetorMaquinasSQL){
                InputStream imagemStream = maquina.getImagem();
                if (imagemStream != null){
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;

                    while ((length = imagemStream.read(buffer)) != -1) {
                        baos.write(buffer, 0, length);
                    }
                    byte[] bytes = baos.toByteArray();
                    String base64String = java.util.Base64.getEncoder().encodeToString(bytes);
                    maquina.setUrlImagem(base64String);
                } else {
                    maquina.setUrlImagem("none");
                }
            }
        }
        request.setAttribute("vetorMaquinas", vetorMaquinasSQL);
        request.getRequestDispatcher("WEB-INF/paginas/gestaoMaquina.jsp").forward(request, response);
    }
}