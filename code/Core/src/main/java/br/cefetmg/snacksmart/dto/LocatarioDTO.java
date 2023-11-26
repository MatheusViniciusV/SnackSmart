package br.cefetmg.snacksmart.dto;

/**
 *
 * @author eloym
 */

import br.cefetmg.snacksmart.exceptions.dto.*;
import br.cefetmg.snacksmart.utils.InputValidador;

public class LocatarioDTO implements IUsuarioDTO {

    private int id;
    private String nome;
    private String senha;
    private String cpf;
    private String telefone;
    private String email;

    public LocatarioDTO(String nome, String cpf, String email, String telefone) throws ParametroInvalidoException{
        if(nome.isEmpty()) {
            throw new NomeNuloException();
        } else {
            this.nome = nome;
        }

        if(InputValidador.validaCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new CPFInvalidoException();
        }

        if(InputValidador.validaEmail(email)) {
            this.email = email;
        } else {
            throw new EmailInvalidoException();
        }

        if(InputValidador.validaTelefone(telefone)) {
            this.telefone = telefone;
        } else {
            throw new TelefoneInvalidoException(telefone);
        }
    }

    public LocatarioDTO(String nome, String cpf, String email, String telefone, String senha) throws ParametroInvalidoException {
        if(senha.isEmpty()) {
            throw new SenhaNulaException();
        } else {
            this.senha = senha;
        }

        if(nome.isEmpty()) {
            throw new NomeNuloException();
        } else {
            this.nome = nome;
        }

        if(InputValidador.validaCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new CPFInvalidoException();
        }

        if(InputValidador.validaEmail(email)) {
            this.email = email;
        } else {
            throw new EmailInvalidoException();
        }

        if(InputValidador.validaTelefone(telefone)) {
            this.telefone = telefone;
        } else {
            throw new TelefoneInvalidoException(telefone);
        }
    }

    public LocatarioDTO(int id, String nome, String cpf, String email, String telefone) throws ParametroInvalidoException{
        this.id = id;

        if(nome.isEmpty()) {
            throw new NomeNuloException();
        } else {
            this.nome = nome;
        }

        if(InputValidador.validaCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new CPFInvalidoException();
        }

        if(InputValidador.validaEmail(email)) {
            this.email = email;
        } else {
            throw new EmailInvalidoException();
        }

        if(InputValidador.validaTelefone(telefone)) {
            this.telefone = telefone;
        } else {
            throw new TelefoneInvalidoException(telefone);
        }
    }

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
        if(nome.isEmpty())
            throw new NomeNuloException();

        this.nome = nome;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) throws CPFInvalidoException{
        /*if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"))
            throw new CPFInvalidoException("CPF do gerente invalido.");*/

        this.cpf = cpf;
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