/*
 * Use esse arquivo para testar a conexão
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.utils.bd;

import br.cefetmg.snacksmart.dao.ContratosDAO;
import br.cefetmg.snacksmart.dao.LocatarioDAO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.utils.SenhaManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eloym
 */
public class ConnectionTester {
    public static void main(String[] args) {
        ConnectionManager conn = ConnectionManager.getInstance();
        System.out.println("ok 1");
        
        try {
            Connection cn = conn.getConnection();

            ContratosDAO contratosDAO = new ContratosDAO();
            LocatarioDAO locatarioDAO = new LocatarioDAO();
            contratosDAO.deletarPorId(4);

            System.out.println(SenhaManager.fazHash("loc2"));

            String sql = "SELECT * FROM `gerente`";

            PreparedStatement pstmt = cn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next())
                System.out.println(rs.getString("nome"));

            rs.close();
            pstmt.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionTester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ok 2");
    }
}
