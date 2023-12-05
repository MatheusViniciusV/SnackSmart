package br.cefetmg.snacksmart.controller.gerente;

import java.io.IOException;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.exceptions.dto.ParametroInvalidoException;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.dao.GerenteDAO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.services.gerente.ManterGerente;
import br.cefetmg.snacksmart.utils.SenhaManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AlterarDadosGerente", urlPatterns = {"/AlterarDadosGerente"})
public class AlterarDadosGerente extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ManterGerente acesso = new ManterGerente();
            GerenteDTO gerenteDTO = acesso.obterGerente();
            
            String nome = request.getParameter("nome");
            if(nome.isEmpty())
                nome = gerenteDTO.getNome();

            String cpf = request.getParameter("cpf");
            if(cpf.isEmpty())
                cpf = gerenteDTO.getCPF();

            String senha = request.getParameter("senha");
            if(senha.isEmpty())
                senha = gerenteDTO.getSenha();
            else
                senha = SenhaManager.fazHash(senha);

            String email = request.getParameter("email");
            if(email.isEmpty())
                email = gerenteDTO.getSenha();

            String telefone = request.getParameter("telefone");
            if(telefone.isEmpty())
                telefone = gerenteDTO.getTelefone();

            GerenteDTO gerenteNovo = new GerenteDTO(nome, cpf, senha, email, telefone);

            acesso.atualizarGerente(gerenteNovo);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", gerenteNovo);
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("principal.jsp");
        } catch(PersistenciaException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (ParametroInvalidoException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
