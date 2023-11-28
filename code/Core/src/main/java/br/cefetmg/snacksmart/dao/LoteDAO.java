package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.idao.ILoteDAO;
import br.cefetmg.snacksmart.dto.LoteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.cefetmg.snacksmart.utils.bd.ConnectionManager;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;

public class LoteDAO implements ILoteDAO {
    @Override
    public int inserir(LoteDTO loteDTO) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
         
            String sql = "INSERT INTO lote (tipo_produto, quantidade, preco_compra, preco_venda, fornecedor__fk, imagem, locatario__fk) "
                       + "VALUES (?, ?, ?, ?, ?, ?) ";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, loteDTO.getTipoProduto());
            pstmt.setInt(2, loteDTO.getQuantidade());
            pstmt.setDouble(3, loteDTO.getPrecoCompra());
            pstmt.setDouble(4, loteDTO.getPrecoVenda());
            pstmt.setInt(5, loteDTO.getFornecedor().getId());
            pstmt.setBinaryStream(6, loteDTO.getImagem());
            pstmt.setInt(7, loteDTO.getLocatario().getId());
            pstmt.executeUpdate();
            
            sql = "SELECT `pk` FROM `locatario` ORDER BY `pk` DESC LIMIT 1";
            
            pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            int pk = 0;
            if (rs.next()) {
                pk = rs.getInt("pk");
                loteDTO.setId(pk);
            }

            pstmt.close();
            connection.close();

            return pk;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    public boolean atualizar(LoteDTO loteDTO) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "UPDATE lote SET tipo_produto = ?, quantidade = ?, preco_compra = ?, preco_venda = ?, fornecedor__fk = ?, locatario__fk = ?, imagem = ? WHERE id = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, loteDTO.getTipoProduto());
            pstmt.setInt(2, loteDTO.getQuantidade());
            pstmt.setDouble(3, loteDTO.getPrecoCompra());
            pstmt.setDouble(4, loteDTO.getPrecoVenda());
            pstmt.setInt(5, loteDTO.getFornecedor().getId());
            pstmt.setInt(6, loteDTO.getLocatario().getId());
            pstmt.setBinaryStream(7, loteDTO.getImagem()); 
            pstmt.setInt(8, loteDTO.getId()); 
            
            int rowsAffected = pstmt.executeUpdate();
            
            pstmt.close();
            connection.close();
            
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public boolean delete(int id) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "DELETE FROM lote WHERE id = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id); 
            
            int rowsAffected = pstmt.executeUpdate();
            
            pstmt.close();
            connection.close();
            
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
     @Override
    public ArrayList<LoteDTO> listarTodos() throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM lote";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<LoteDTO> lotes = new ArrayList<>();
            
            while (rs.next()) {
                LoteDTO lote = new LoteDTO();     
                lote.setId(rs.getInt("id"));
                lote.setTipoProduto(rs.getString("tipo_produto"));
                lote.setQuantidade(rs.getInt("quantidade"));
                lote.setPrecoCompra(rs.getDouble("preco_compra"));
                lote.setPrecoVenda(rs.getDouble("preco_venda"));
                lote.setImagem(rs.getBinaryStream("imagem"));
                
                lotes.add(lote);
            }
            
            rs.close();
            pstmt.close();
            connection.close();
            
            return lotes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public LoteDTO consultarPorId(int id) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM lote WHERE id = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            LoteDTO lote = null;
            
            if (rs.next()) {
                lote = new LoteDTO();
                lote.setId(rs.getInt("id"));
                lote.setTipoProduto(rs.getString("tipo_produto"));
                lote.setQuantidade(rs.getInt("quantidade"));
                lote.setPrecoCompra(rs.getDouble("preco_compra"));
                lote.setPrecoVenda(rs.getDouble("preco_venda"));
                lote.setImagem(rs.getBinaryStream("imagem"));
            }
            
            rs.close();
            pstmt.close();
            connection.close();
            
            return lote;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<LoteDTO> listarPorLocatario(String cpf) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT l.* FROM lote l INNER JOIN locatario lo ON l.locatario__fk = lo.id WHERE lo.cpf = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<LoteDTO> lotes = new ArrayList<>();
            
            while (rs.next()) {
                LoteDTO lote = new LoteDTO();
                lote.setId(rs.getInt("id"));
                lote.setTipoProduto(rs.getString("tipo_produto"));
                lote.setQuantidade(rs.getInt("quantidade"));
                lote.setPrecoCompra(rs.getDouble("preco_compra"));
                lote.setPrecoVenda(rs.getDouble("preco_venda"));
                lote.setImagem(rs.getBinaryStream("imagem"));
                lotes.add(lote);
            }
            
            rs.close();
            pstmt.close();
            connection.close();
            
            return lotes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
