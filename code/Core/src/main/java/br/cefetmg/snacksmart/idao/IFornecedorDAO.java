package br.cefetmg.snacksmart.idao;

import br.cefetmg.snacksmart.dto.FornecedorDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import java.util.ArrayList;

public interface IFornecedorDAO {
    
    int inserir(FornecedorDTO fornecedorDTO) throws PersistenciaException;

    boolean atualizar(FornecedorDTO fornecedorDTO) throws PersistenciaException;

    boolean delete(int id) throws PersistenciaException;

    ArrayList<FornecedorDTO> listarTodos() throws PersistenciaException;
    
    ArrayList<FornecedorDTO> listarPorLocatario(String cpf) throws PersistenciaException;
}
