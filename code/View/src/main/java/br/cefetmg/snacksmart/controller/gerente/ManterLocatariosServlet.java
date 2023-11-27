package br.cefetmg.snacksmart.controller.gerente;

import java.io.IOException;
import java.io.PrintWriter;

import br.cefetmg.snacksmart.exceptions.dto.CPFInvalidoException;
import br.cefetmg.snacksmart.exceptions.dto.ParametroInvalidoException;
import br.cefetmg.snacksmart.services.gerente.ManterLocatarios;
import br.cefetmg.snacksmart.utils.SenhaManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.cefetmg.snacksmart.dao.LocatarioDAO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "manterLocatarios", urlPatterns = {"/manterLocatarios"})
public class ManterLocatariosServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ManterLocatarios acesso = new ManterLocatarios();

            LocatarioDTO locatario;
            
            String submitType = request.getParameter("submitType");

            if (null != submitType) {
                String nome = request.getParameter("nome");
                String cpf = request.getParameter("cpf");
                String email = request.getParameter("email");
                String telefone = request.getParameter("telefone");
                String senha = request.getParameter("senha");
                switch (submitType) {
                    case "adicionar":
                        locatario = new LocatarioDTO(
                                nome,
                                cpf,
                                email,
                                telefone,
                                senha
                        );

                        acesso.registrar(locatario);
                        response.setStatus(HttpServletResponse.SC_OK);
                        break;
                    case "atualizar":

                        if(cpf.isEmpty())
                            throw new CPFInvalidoException("campo de CPF vazio.");

                        locatario = acesso.buscaPorCpf(cpf);

                        if(!senha.isEmpty())
                            locatario.setSenha(SenhaManager.fazHash(senha));

                        if(!email.isEmpty())
                            locatario.setEmail(email);

                        if(!telefone.isEmpty())
                            locatario.setTelefone(telefone);
                        
                        if(!nome.isEmpty()) 
                            locatario.setNome(nome);
                        
                        System.out.println(locatario.getNome());


                        acesso.atualizar(locatario);
                        response.setStatus(HttpServletResponse.SC_OK);
                        break;
                }
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(ManterLocatariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParametroInvalidoException px) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, px.getMessage());
        }
    }
}
