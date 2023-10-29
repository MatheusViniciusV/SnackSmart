package br.cefetmg.snacksmart.dao;

import br.cefetmg.snacksmart.conexao.Conexao;
import br.cefetmg.snacksmart.dto.MaquinaDTO;
import br.cefetmg.snacksmart.idao.IMaquinaDAO;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author marcos
 */
public class MaquinaDAO implements IMaquinaDAO {
    
    
    @Override
    public void set(MaquinaDTO maquina){
        String sql = "INSER INTO MAQUINA (CODIGO, LOCALIZACAO, VALOR, ESTADO) VALUES(?, ?, ?, ?)";
        
        PreparedStatement ps = null;
        try{
            ps = Conexao.getConexao().preparedStatement(sql);
            ps.setString(1,maquina.getCodigo());
            ps.setString(2,maquina.getLocalizacao());
            ps.setString(3,maquina.getValor());
            ps.setString(4,maquina.getEstado());
            
            ps.execute();
            ps.close();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public MaquinaDTO get(){
        //ainda sera implementrado
        return null;
   }

    @Override
    public ArrayList<MaquinaDTO> listarTodos() {
        ArrayList<MaquinaDTO> ArrayList = null ;
        return ArrayList;
    }
}
