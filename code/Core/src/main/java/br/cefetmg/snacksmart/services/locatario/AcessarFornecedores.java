package br.cefetmg.snacksmart.services.locatario;

import br.cefetmg.snacksmart.dao.FornecedorDAO;
import br.cefetmg.snacksmart.dto.FornecedorDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.idao.IFornecedorDAO;

import java.util.ArrayList;

public class AcessarFornecedores {
    private final IFornecedorDAO fornecedorDAO;
    public AcessarFornecedores() {
        fornecedorDAO = new FornecedorDAO();
    }
    
    public ArrayList<FornecedorDTO> getAllFornecedores(String cpf) throws PersistenciaException{
        ArrayList fornecedores = fornecedorDAO.listarPorLocatario(cpf);
        return fornecedores;
    }
    
    public ArrayList<FornecedorDTO> getAllFornecedores() throws PersistenciaException{
        ArrayList fornecedores = fornecedorDAO.listarTodos();
        return fornecedores;
    }
    
    public void atualizarFornecedor(FornecedorDTO fornecedor) throws PersistenciaException{        
        fornecedorDAO.atualizar(fornecedor);
    }
    
    public void adicionarFornecedor(String nome, String telefone, String email, LocatarioDTO locatario) throws PersistenciaException{
        ArrayList fornecedores = getAllFornecedores();
        int id = 1;
        if (fornecedores != null)
            id = fornecedores.size() + 1;
        
        FornecedorDTO fornecedorDTO = new FornecedorDTO(id, nome, telefone, email, locatario);
        fornecedorDAO.inserir(fornecedorDTO);
    }
    
    public void removerFornecedor(FornecedorDTO fornecedor) throws PersistenciaException{
        fornecedorDAO.delete(fornecedor);
    }
}
