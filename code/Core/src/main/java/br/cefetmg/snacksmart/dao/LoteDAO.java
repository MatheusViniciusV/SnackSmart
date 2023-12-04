package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.dto.FornecedorDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
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
                       + "VALUES (?, ?, ?, ?, ?, ?, ?) ";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, loteDTO.getTipoProduto());
            pstmt.setInt(2, loteDTO.getQuantidade());
            pstmt.setDouble(3, loteDTO.getPrecoCompra());
            pstmt.setDouble(4, loteDTO.getPrecoVenda());
            pstmt.setInt(5, loteDTO.getFornecedor().getId());
            pstmt.setBinaryStream(6, loteDTO.getImagem());
            pstmt.setInt(7, loteDTO.getLocatario().getId());
            pstmt.executeUpdate();
            
            sql = "SELECT `pk` FROM `lote` ORDER BY `pk` DESC LIMIT 1";
            
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
    
    @Override
    public boolean atualizar(LoteDTO loteDTO) throws PersistenciaException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE lote SET "
                        + "tipo_produto = ?, "
                        + "quantidade = ?, "
                        + "preco_compra = ? "
                        + "preco_venda = ? "
                        + "fornecedor__fk = ? "
                        + "imagem = ? "
                        + "locatario__fk = ? "
                        + "WHERE pk = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, loteDTO.getTipoProduto());
            pstmt.setInt(2, loteDTO.getQuantidade());
            pstmt.setDouble(3, loteDTO.getPrecoCompra());
            pstmt.setDouble(4, loteDTO.getPrecoVenda());
            pstmt.setInt(5, loteDTO.getFornecedor().getId());
            pstmt.setBinaryStream(6, loteDTO.getImagem());
            pstmt.setInt(7, loteDTO.getLocatario().getId());
            pstmt.setInt(8, loteDTO.getId());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public boolean delete(int id) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "DELETE FROM lote WHERE pk = ?";
            
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
            
            String sql = "SELECT * FROM lote ORDER BY pk";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<LoteDTO> lotes = null;
            if (rs.next()) {
                lotes = new ArrayList<>();
                do {
                    LoteDTO lote = new LoteDTO();     
                    lote.setId(rs.getInt("pk"));
                    lote.setTipoProduto(rs.getString("tipo_produto"));
                    lote.setQuantidade(rs.getInt("quantidade"));
                    lote.setPrecoCompra(rs.getDouble("preco_compra"));
                    lote.setPrecoVenda(rs.getDouble("preco_venda"));
                    lote.setImagem(rs.getBinaryStream("imagem"));
                    int locatarioId = rs.getInt("locatario__fk");               
                    LocatarioDAO locatarioDAO = new LocatarioDAO();
                    LocatarioDTO locatario = locatarioDAO.consultarPorId(locatarioId);
                    lote.setLocatario(locatario);
                    int fornecedorId = rs.getInt("fornecedor__fk");               
                    FornecedorDAO fornecedorDAO = new FornecedorDAO();
                    FornecedorDTO fornecedor = fornecedorDAO.consultarPorId(fornecedorId);
                    lote.setFornecedor(fornecedor);
                    lotes.add(lote);
                } while (rs.next());
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
                lote.setId(rs.getInt("pk"));
                lote.setTipoProduto(rs.getString("tipo_produto"));
                lote.setQuantidade(rs.getInt("quantidade"));
                lote.setPrecoCompra(rs.getDouble("preco_compra"));
                lote.setPrecoVenda(rs.getDouble("preco_venda"));
                lote.setImagem(rs.getBinaryStream("imagem"));
                int locatarioId = rs.getInt("locatario__fk");               
                LocatarioDAO locatarioDAO = new LocatarioDAO();
                LocatarioDTO locatario = locatarioDAO.consultarPorId(locatarioId);
                lote.setLocatario(locatario);
                int fornecedorId = rs.getInt("fornecedor__fk");               
                FornecedorDAO fornecedorDAO = new FornecedorDAO();
                FornecedorDTO fornecedor = fornecedorDAO.consultarPorId(fornecedorId);
                lote.setFornecedor(fornecedor);
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
    public ArrayList<LoteDTO> listarPorLocatario(int id) throws PersistenciaException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM lote WHERE locatario__fk = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<LoteDTO> lotes = new ArrayList<>();
            
            while (rs.next()) {
                LoteDTO lote = new LoteDTO();
                lote.setId(rs.getInt("pk"));
                lote.setTipoProduto(rs.getString("tipo_produto"));
                lote.setQuantidade(rs.getInt("quantidade"));
                lote.setPrecoCompra(rs.getDouble("preco_compra"));
                lote.setPrecoVenda(rs.getDouble("preco_venda"));
                lote.setImagem(rs.getBinaryStream("imagem"));
                int locatarioId = rs.getInt("locatario__fk");               
                LocatarioDAO locatarioDAO = new LocatarioDAO();
                LocatarioDTO locatario = locatarioDAO.consultarPorId(locatarioId);
                lote.setLocatario(locatario);
                int fornecedorId = rs.getInt("fornecedor__fk");               
                FornecedorDAO fornecedorDAO = new FornecedorDAO();
                FornecedorDTO fornecedor = fornecedorDAO.consultarPorId(fornecedorId);
                lote.setFornecedor(fornecedor);
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
