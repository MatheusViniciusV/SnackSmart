package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.utils.bd.ConnectionTester;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eloym
 */
public class GerenteDAO implements IGerenteDAO {
    @Override
    public GerenteDTO get() throws PersistenciaException {
        ConnectionManager conn = ConnectionManager.getInstance();
        GerenteDTO gerente = null;
        try {
            Connection cn = conn.getConnection();

            String sql = "SELECT * FROM `gerente`";

            PreparedStatement pstmt = cn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                gerente = new GerenteDTO(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("senha"),
                        null,
                        rs.getString("email"),
                        rs.getString("telefone"));
            }

            rs.close();
            pstmt.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionTester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return gerente;
    }
    
    @Override
    public int set(GerenteDTO gerente) throws PersistenciaException {
        try {
            delete();

            Connection conexao = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO `gerente` (`pk`, `nome`, `senha`, `cpf`, `telefone`, `email`) VALUES ('1',?,?,?,?,?)";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, gerente.getNome());
            pstmt.setString(2, gerente.getSenha());
            pstmt.setString(3, gerente.getCPF());
            pstmt.setString(4, gerente.getTelefone());
            pstmt.setString(5, gerente.getEmail());

            int retorno = pstmt.executeUpdate();
            pstmt.close();
            conexao.close();

            return retorno;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete() throws PersistenciaException {
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM `gerente` WHERE pk = 1";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.executeUpdate();

            pstmt.close();
            conexao.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
