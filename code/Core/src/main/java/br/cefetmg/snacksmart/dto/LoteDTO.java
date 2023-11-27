package br.cefetmg.snacksmart.dto;

public class LoteDTO {
    
    private int id;
    private String tipoProduto;
    private int quantidade;
    private double precoCompra;
    private double precoVenda;
    private FornecedorDTO fornecedor;
    private LocatarioDTO locatario;
    
    public LoteDTO() {};

    public LoteDTO(int id, String tipoProduto, int quantidade, double precoCompra, double precoVenda,
                      FornecedorDTO fornecedor, LocatarioDTO locatario) {
        this.id = id;
        this.tipoProduto = tipoProduto;
        this.quantidade = quantidade;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.fornecedor = fornecedor;
        this.locatario = locatario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public FornecedorDTO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorDTO fornecedor) {
        this.fornecedor = fornecedor;
    }

    public LocatarioDTO getLocatario() {
        return locatario;
    }

    public void setLocatario(LocatarioDTO locatario) {
        this.locatario = locatario;
    }
}
