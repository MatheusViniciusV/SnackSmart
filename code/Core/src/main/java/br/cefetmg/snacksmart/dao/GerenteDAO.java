package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.sql.SQLException;

/**
 *
 * @author eloym
 */
public class GerenteDAO implements IGerenteDAO {
    @Override
    public GerenteDTO get() throws PersistenciaException {
        try {
            Connection conexao = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM `gerente` ";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            GerenteDTO gerente = null;
            if (rs.next()) {
                System.out.println(rs.getString("nome"));
                gerente = new GerenteDTO(
                                        rs.getString("nome"),
                                        rs.getString("cpf"),
                                        rs.getString("senha"));
            }

            rs.close();
            pstmt.close();
            conexao.close();
            
            return gerente;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public void set(GerenteDTO gerente) throws PersistenciaException {
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

            pstmt.executeUpdate();
            pstmt.close();
            conexao.close();

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
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
