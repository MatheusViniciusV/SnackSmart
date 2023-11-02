package br.cefetmg.snacksmart.utils.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author eloym
 */
public class MysqlConnection implements ConnectionFactory {
    private final static String dbDriver = "org.postgresql.Driver";
    private final static String dbURL = "jdbc:mysql://localhost:3306/snacksmart";
    private final static String user = "root";
    private final static String pass = "projeto";
    
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL, user, pass);
    }
}
