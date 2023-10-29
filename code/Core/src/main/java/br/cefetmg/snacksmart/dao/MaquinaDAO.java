package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.conexao.Conexao;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author marcos
 */
public class MaquinaDAO implements IMaquinaDAO {
    
    
    @Override
    public void set(MaquinaDTO maquina){
        String sql = "INSER INTO MAQUINAS (CODIGO, LOCALIZACAO, VALOR, ESTADO) VALUES(?, ?, ?, ?)";
        
        PreparedStatement ps = null;
        try{
            ps = Conexao.getConexao().preparedStatement(sql);
            ps.setString(1,maquina.getCodigo());
            ps.setString(2,maquina.getLocalizacao());
            ps.setString(3, maquina.getValor());
            ps.setString(4,maquina.getEstado());
            
            ps.execute();
            ps.close();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public MaquinaDTO get(int codigo){
         String sql = "SELECT * FROM MAQUINAS";
         
          MaquinaDTO maquina = new MaquinaDTO();
         PreparedStatement ps = null;
         ResultSet rset = null;
         try{
          ps = Conexao.getConexao().preparedStatement(sql);
          rset = ps.executeQuery();
          
          maquina.setCodigo(rset.getInt("codigo"));
          maquina.setLocalizacao(rset.getString("localizacao"));
          maquina.setValor(rset.getDouble("valor"));
          maquina.setEstado(rset.getInt("estado"));
         
         }catch(SQLException ex){
            ex.printStackTrace();
         }finally{
            try {
                if(rset!=null)
                    rset.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
         }
        return maquina;
   }

    @Override
    public ArrayList<MaquinaDTO> listarTodos() {
        ArrayList<MaquinaDTO> ArrayList = new ArrayList<MaquinaDTO>();
        String sql = "SELECT * FROM MAQUINAS";
         
         PreparedStatement ps = null;
         ResultSet rset = null;
         try{
          ps = Conexao.getConexao().preparedStatement(sql);
          rset = ps.executeQuery();
          while(rset.next()){
              MaquinaDTO maquina = new MaquinaDTO();
              
              maquina.setCodigo(rset.getInt("codigo"));
              maquina.setLocalizacao(rset.getString("localizacao"));
              maquina.setValor(rset.getDouble("valor"));
              maquina.setEstado(rset.getInt("estado"));
              
               ArrayList.add(maquina);
          }
         }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try {
                if(rset!=null)
                    rset.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
         }
         return ArrayList;
    }
}
