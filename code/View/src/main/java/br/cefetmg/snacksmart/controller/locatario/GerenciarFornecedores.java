package br.cefetmg.snacksmart.controller.locatario;

import br.cefetmg.snacksmart.dto.FornecedorDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.gerente.ManterLocatarios;
import br.cefetmg.snacksmart.services.locatario.AcessarFornecedores;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "GerenciarFornecedores", urlPatterns = {"/GerenciarFornecedores"})
public class GerenciarFornecedores extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AcessarFornecedores acessoFornecedores = new AcessarFornecedores();
        ManterLocatarios acessoLocatario = new ManterLocatarios();
        String form = request.getParameter("addForm");
        String remocao = request.getParameter("remocao");
        
        if ("addForm".equals(form)){
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String locatarioCPF = request.getParameter("locatario");
            LocatarioDTO locatario;
            try {
                locatario = acessoLocatario.buscaPorCpf(locatarioCPF);
                acessoFornecedores.adicionarFornecedor(nome, telefone, email, locatario);
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarFornecedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("solicitado".equals(remocao)){ 
           try {
                String id = request.getParameter("idFornecedor");
                acessoFornecedores.removerFornecedor(Integer.parseInt(id));
            } catch (PersistenciaException ex) {
                Logger.getLogger(GerenciarFornecedores.class.getName()).log(Level.SEVERE, null, ex);
            }              
        }
        else {
            String nome = request.getParameter("nomeExibido");
            String telefone = request.getParameter("telefoneExibido");
            String email = request.getParameter("emailExibido");
            String locatarioCPF = request.getParameter("locatarioExibido");
            String id = request.getParameter("idFornecedor");             
            LocatarioDTO locatario;
            try {
                locatario = acessoLocatario.buscaPorCpf(locatarioCPF);
                FornecedorDTO fornecedor = new FornecedorDTO (Integer.parseInt(id), nome, telefone, email, locatario);
                acessoFornecedores.atualizarFornecedor(fornecedor);
            } catch (PersistenciaException ex) {   
                Logger.getLogger(GerenciarFornecedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        
        ArrayList<FornecedorDTO> vetorFornecedorSQL = null;
        HttpSession session = request.getSession();
        LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");
        try {
            vetorFornecedorSQL = acessoFornecedores.getAllFornecedores(locatario.getCPF());
        } catch (PersistenciaException ex) {
            Logger.getLogger(GerenciarFornecedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("usuarioResponsavel", locatario);
        request.setAttribute("vetorFornecedores", vetorFornecedorSQL);
        request.getRequestDispatcher("WEB-INF/paginas/gestaoFornecedor.jsp").forward(request, response);           
    }
}
