package br.cefetmg.snacksmart.facade;

import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.services.gerente.AcessarMaquinas;
import java.io.IOException;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.gerente.ManterLocatarios;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;


/**
 *
 * @author Aluno
 */
@WebServlet(name = "GestaoMaquina", urlPatterns = {"/gestaoMaquina"})
public class GestaoMaquina extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        AcessarMaquinas acesso = new AcessarMaquinas();
        
        ArrayList<MaquinaDTO> vetorMaquinasSQL = null;
        ManterLocatarios acessoLocatario = new ManterLocatarios();     
        HttpSession session = request.getSession();
        TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
        
        if (tipoUsuario == TipoUsuario.LOCADOR){
            try {
                vetorMaquinasSQL = acesso.getAllMaquinasGerente();
                request.setAttribute("listaLocatarios", acessoLocatario.listaLocatarios());
            } catch (PersistenciaException ex) {
                Logger.getLogger(GestaoMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");
            try {               
                vetorMaquinasSQL = acesso.getAllMaquinasLocatario(locatario.getId());
            } catch (PersistenciaException ex) {
                Logger.getLogger(GestaoMaquina.class.getName()).log(Level.SEVERE, null, ex);
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
