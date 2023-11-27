package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.idao.ILoteDAO;
import br.cefetmg.snacksmart.dto.LoteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException

public class LoteDAO {
    
    @Override
    public int inserir(LoteDTO loteDTO) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
         
            String sql = "INSERT INTO lote (tipo_produto, senha, cpf, rg, telefone, email) "
                       + "VALUES (?, ?, ?, ?, ?, ?) ";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, locatarioDTO.getNome());
            pstmt.setString(2, locatarioDTO.getSenha());
            pstmt.setString(3, locatarioDTO.getCPF());
            pstmt.setString(4, locatarioDTO.getRG());
            pstmt.setString(5, locatarioDTO.getTelefone());
            pstmt.setString(6, locatarioDTO.getEmail());
            pstmt.executeUpdate();
            
            sql = "SELECT `pk` FROM `locatario` ORDER BY `pk` DESC LIMIT 1";
            
            pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            int pk = 0;
            if (rs.next()) {
                pk = rs.getInt("pk");
                locatarioDTO.setId(pk);
            }

            pstmt.close();
            connection.close();

            return pk;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
