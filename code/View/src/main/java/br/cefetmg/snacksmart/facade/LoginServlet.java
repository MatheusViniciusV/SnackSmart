package br.cefetmg.snacksmart.facade;

import br.cefetmg.snacksmart.dto.IUsuario;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.ValidadorUsuario;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ValidadorUsuario validador = new ValidadorUsuario();
            
            String cpf = request.getParameter("usuario");
            String senha = request.getParameter("senha");
            

            System.out.println(cpf);
            
            TipoUsuario tipoUsuario = validador.tipoUsuario(cpf);
            
            
            HttpSession session = request.getSession();
            
            switch(tipoUsuario) {
                case NAO_CADASTRADO: 
                    response.sendRedirect("index.html"); 
                    break;
                case LOCADOR: // gerente
                    if(validador.validarGerente(cpf, senha)) {
                        response.sendRedirect("principal.jsp");
                        session.setAttribute("tipoUsuario", tipoUsuario);
                        session.setAttribute("usuario", validador.getGenrente());
                    } else {
                        response.sendRedirect("index.html");
                    }
                    break;
                case LOCATARIO: // locatário
                    if(validador.validarLocatario(cpf, senha)) {
                        response.sendRedirect("principal.jsp");
                        session.setAttribute("tipoUsuario", tipoUsuario);
                        session.setAttribute("usuario", null);
                    } else {
                        response.sendRedirect("index.html");
                    }
                    break;
            }

            session.setAttribute("LOCATARIO", TipoUsuario.LOCATARIO);
            session.setAttribute("LOCADOR", TipoUsuario.LOCADOR);
            session.setMaxInactiveInterval(15000);
        } catch (PersistenciaException | UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        
        if(sessao != null) 
            sessao.invalidate();
        
        response.sendRedirect("index.html");
    }
//
//    //Retorna 0 se o login é inválido, 1 se o login é do gerente e 2 se o login é do locatário
//    private int validarLogin(String usuario, String senha) {
//        
//        //Por enquanto, nesse validar login, estou supondo que o banco de dados será MySQL
//        //Depois substituir esses campos quando Maffort passar o modelo do BD
//        String dbUrl = "jdbc:mysql://localhost:porta/bancodedados";
//        String dbUser = "seuusuario";
//        String dbPassword = "suasenha";
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//            
//            String query = "SELECT * FROM gerente WHERE cpf = ? AND token = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, usuario);
//            preparedStatement.setString(2, senha);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                connection.close();
//                return 1; // Usuário é um gerente
//            }
//            
//            query = "SELECT * FROM locatario WHERE cpf = ? AND token = ?";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, usuario);
//            preparedStatement.setString(2, senha);
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                connection.close();
//                return 2; // Usuário é um locatário
//            }
//    
//        //Futuramente, é interessante lançar e pegar outros tipos de exceções específicas
//        } catch (Exception e) { 
//            e.printStackTrace();
//        }
//        
//        return 0; // Usuário inválido
//    }
}
