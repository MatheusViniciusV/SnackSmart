package br.cefetmg.snacksmart.dto;

// Importe das exceções usadas


/**
 *
 * @author eloym
 */
public class GerenteDTO implements IUsuarioDTO {
    private String nome,
            cpf,
            senha,
            email,
            telefone,
            rg;
    
    public GerenteDTO(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public GerenteDTO(String nome, String cpf, String senha, String rg, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.rg = rg;
        this.email = email;
        this.telefone = telefone;
    }
        
//    public void setCPF(String cpf) throws CPFInvalidoException{
//        if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"))
//            throw new CPFInvalidoException("CPF do gerente invalido.");
//
//        novoCPF = cpf;
//    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return cpf;
    }

    public String getEmail() { return email; }

    public String getTelefone() { return telefone; }

    public String getRG() { return rg; }

    public String getSenha() {
        return senha;
    }
//
//    public void SetNome(String nome) throws NomeNuloException {
//        if(nome.length() == 0)
//            throw new NomeNuloException("Nome do Gerente não deve ser nulo.");
//
//        novoNome = nome;
//    }
    

    
//  Define com um senha aleatório e retorna esse novo tonken;
//    public String definirSenha()  throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//
//        Random random = new Random();
//
//        StringBuilder senhaAleatorio = new StringBuilder();
//
//        for (int i = 0; i < 8; i++) {
//            int indice = random.nextInt(caracteres.length());
//            char caracterAleatorio = caracteres.charAt(indice);
//            senhaAleatorio.append(caracterAleatorio);
//        }
//
//        String senha = senhaAleatorio.toString();
//        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
//        byte hash[] = algorithm.digest(senha.getBytes("UTF-8"));
//
//        StringBuilder aux = new StringBuilder();
//        for (byte b : hash) {
//            aux.append(String.format("%02X", 0xFF & b));
//        }
//
//        novoSenha = aux.toString();
//
//        return novoSenha;
//    }
//
////  Define com um senha passado e retorna esse tonken
//    public void definirSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException, SenhaInvalidaTamanhoException {
//        if(senha.length() < 8)
//            throw new SenhaInvalidaTamanhoException("Senha deve ter mais de 8 caracteres.");
//
//        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
//        byte hash[] = algorithm.digest(senha.getBytes("UTF-8"));
//
//        StringBuilder aux = new StringBuilder();
//        for (byte b : hash) {
//            aux.append(String.format("%02X", 0xFF & b));
//        }
//
//        novoSenha = aux.toString();
//    }
    

    
//    public void aplicarMudanças() throws FilaAteracoesVaziaException, PersistenciaException {
//        String novoNome = (this.novoNome.length() == 0) ? this.nome : this.novoNome;
//        String novoCPF = (this.novoCPF.length() == 0) ? this.cpf : this.novoCPF;
//        String novoSenha  = (this.novoSenha.length() == 0) ? this.senha : this.novoSenha;
//
//
//        if(this.novoNome.length() != 0 || this.novoCPF.length() != 0 || this.novoSenha.length() != 0) {
//            GerenteDTO novoGerente = new GerenteDTO(novoNome, novoCPF, novoSenha);
//            IGerenteDAO dao = new GerenteDAO();
//
//
//            dao.set(novoGerente);
//            // imcompleto, irei aguardar o banco de dados para colocar exceptions entre outras coisas.
//
//            cpf = novoCPF;
//            nome = novoNome;
//            senha = novoSenha;
//        } else {
//            throw new FilaAteracoesVaziaException("Nenhuma alteração foi especificada. Pelo menos um dos atributos (nome, CPF ou senha) deve ser modificado para aplicar alterações.");
//        }
//    }
}
