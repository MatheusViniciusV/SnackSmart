package br.cefetmg.snacksmart.service_maquinas;

import java.io.IOException;
import jakarta.servlet.http.Part;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.cefetmg.snacksmart.dao.MaquinaDAO;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.utils.enums.StatusMaquina;
//Exceptions
import br.cefetmg.snacksmart.exceptions.service_maquinas.FormatoArquivoInvalidoException;

@WebServlet(urlPatterns = {"/service_maquina"})
public class service_maquina extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet service_maquina</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet ta rodando</h1>");
        out.println("</body>");
        out.println("</html>");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        //PrintWriter out = response.getWriter();  
        MaquinaDAO maquinaDAO = new MaquinaDAO();
        String formulario = request.getParameter("formulario");
    
        if ("formAddMaquina".equals(formulario)) { 
            String nome = request.getParameter("nome");
            String tipo = request.getParameter("tipo");
            String locatario = request.getParameter("locatario");
            String localizacao = request.getParameter("localizacao");
            String codigo = gerarCodigo(maquinaDAO);
            StatusMaquina status = StatusMaquina.DISPONIVEL;
            try {
                Part imagemPart = request.getPart("imagem");
                byte[] imagemBytes = imagemPart.getInputStream().readAllBytes();
                MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, imagemBytes, tipo, localizacao, locatario, status); 
                maquinaDAO.set(maquinaDTO);
                throw new FormatoArquivoInvalidoException("O formato do arquivo é inválido.");
            } catch (FormatoArquivoInvalidoException e){
                MaquinaDTO maquinaDTO = new MaquinaDTO(nome, codigo, null, tipo, localizacao, locatario, status); 
                maquinaDAO.set(maquinaDTO);
            }   
            
        } else if ("remocaoMaquina".equals(formulario)) {
            String codigo = request.getParameter("removerMaquinaCodigo");
            maquinaDAO.remove(codigo);
               
        } else if ("formAtualizarMaquina".equals(formulario)) {
            String codigo = request.getParameter("atualizarMaquinaCodigo");
            MaquinaDTO maquinaDTO = maquinaDAO.get(codigo); 
            
            String novoNome = request.getParameter("novoNome");
            if (novoNome != null)
                maquinaDTO.setNome(novoNome);
                        
            String novaLocalizacao = request.getParameter("novaLocalizacao");
            if (novaLocalizacao != null)
                maquinaDTO.setLocalizacao(novaLocalizacao);
            
            String novoLocatario = request.getParameter("novoLocatario");
            if (novoLocatario != null)
                maquinaDTO.setLocatarioResponsavel(novoLocatario);
            
            String statusStr = request.getParameter("status");
            StatusMaquina status = StatusMaquina.fromString(statusStr);
            maquinaDTO.setStatus(status);
            
            try {
                Part imagemPart = request.getPart("novaImagem");
                byte[] novaImagemBytes = imagemPart.getInputStream().readAllBytes();
                maquinaDTO.setImagem(novaImagemBytes);
                maquinaDAO.update(codigo, maquinaDTO);
                throw new FormatoArquivoInvalidoException("O formato do arquivo é inválido.");
            } catch (FormatoArquivoInvalidoException e){
                maquinaDAO.update(codigo, maquinaDTO);
            }          
        } 
    }
    
    private String gerarCodigo(MaquinaDAO maquinaDAO){
        int num = maquinaDAO.getAll().size() + 1;
        String codigo = String.format("%04d", num); 
        return codigo; 
    }
    
    
}
