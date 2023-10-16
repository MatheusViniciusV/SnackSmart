package br.cefetmg.snacksmart.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author mathe
 */

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        int codigologin = validarLogin(usuario, senha);

        HttpSession session = request.getSession();
        session.setAttribute("tipousuario", codigologin);
        
        switch(codigologin) {
            case 0: //inválido
                response.sendRedirect("index.html?error=1"); // Redireciona para a página de login com mensagem de erro
                break;
            case 1: // gerente
                response.sendRedirect("gerenteprincipal.jsp"); // Redireciona para a página principal do gerente
                break;
            case 2: // locatário
                response.sendRedirect("locatarioprincipal.jsp"); // Redireciona para a página principal do locatario
                break;
        }
    }

    //Retorna 0 se o login é inválido, 1 se o login é do gerente e 2 se o login é do locatário
    private int validarLogin(String usuario, String senha) {
        
        //Por enquanto, nesse validar login, estou supondo que o banco de dados será MySQL
        //Depois substituir esses campos quando Maffort passar o modelo do BD
        String dbUrl = "jdbc:mysql://localhost:porta/bancodedados";
        String dbUser = "seuusuario";
        String dbPassword = "suasenha";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            
            String query = "SELECT * FROM gerente WHERE cpf = ? AND token = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, senha);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                connection.close();
                return 1; // Usuário é um gerente
            }
            
            query = "SELECT * FROM locatario WHERE cpf = ? AND token = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, senha);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                connection.close();
                return 2; // Usuário é um locatário
            }
    
        //Futuramente, é interessante lançar e pegar outros tipos de exceções específicas
        } catch (Exception e) { 
            e.printStackTrace();
        }
        
        return 0; // Usuário inválido
    }
}
