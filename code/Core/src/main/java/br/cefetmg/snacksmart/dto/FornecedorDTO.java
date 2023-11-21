package br.cefetmg.snacksmart.dto;

public class FornecedorDTO {
    
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private LocatarioDTO locatario;
    
    public FornecedorDTO() {}
    
    public FornecedorDTO(int id, String nome, String telefone, String email, LocatarioDTO locatario){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.locatario = locatario;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
    
    public LocatarioDTO getLocatario() {
        return this.locatario;
    }

    public void setLocatario(LocatarioDTO locatario) {
        this.locatario = locatario;
    }

    @Override
    public String toString() {
        return nome;
    }
}
