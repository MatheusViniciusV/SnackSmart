package br.cefetmg.snacksmart.dto;

/**
 *
 * @author eloym
 */

import br.cefetmg.snacksmart.exceptions.dto.NomeNuloException;
import br.cefetmg.snacksmart.exceptions.dto.CPFInvalidoException;

public class LocatarioDTO implements IUsuarioDTO {

    private int id;
    private String nome;
    private String senha;
    private String cpf;
    private String rg;
    private String telefone;
    private String email;

    public LocatarioDTO(String nome){
        this.nome = nome;
    }

    public LocatarioDTO(String nome, String cpf, String email, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public LocatarioDTO(int id, String nome, String cpf, String email, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public LocatarioDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws NomeNuloException {
        if(nome.length() == 0)
            throw new NomeNuloException("Nome do locatário não deve ser nulo.");

        this.nome = nome;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) throws CPFInvalidoException{
        if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"))
            throw new CPFInvalidoException("CPF do gerente invalido.");

        this.cpf = cpf;
    }

    public String getRG() {
        return (this.rg == null) ? "" : this.rg;
    }

    public void setRG(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return (this.telefone == null) ? "" : this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return (this.email == null) ? "" : this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nome;
    }
}