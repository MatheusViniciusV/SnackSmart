package br.cefetmg.snacksmart.conexao;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author marco
 */
public class Conexao {
    
    private static final String url = "jdb:mysql://localhoost:3306/seubancodedados";
    private static final String user = "seuuser";
    private static final String password = "seupassword";
    
    private static Connection conn;
    
    public static Connection getConexao(){
       try { 
            if(conn == null){
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }else{
                return conn;
            }
        }catch (SQLException ex) {
               ex.printStackTrace();
               return null;
            }
    }
}
