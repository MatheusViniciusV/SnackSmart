package br.cefetmg.snacksmart.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EstabelecerConexao {
    private static final String URL = "jdbc:mysql://localhost:3306/snacksmart";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection obterConexao() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não foi possível realizar essa ação, erro: " + e);
        }

        return connection;
    }

    public static void fecharConexao(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Não foi possível realizar essa ação, erro: " + e);
        }
    }
}
