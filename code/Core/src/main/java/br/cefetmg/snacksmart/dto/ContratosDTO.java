/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.dto;

import java.time.LocalDate;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.dao.GerenteDAO;

/**
 *
 * @author eloym
 */
public class ContratosDTO {
    private long id;
    private double valor;
    private LocalDate dataInicio;
    private LocalDate dataExpiracao;
    private LocalDate dataPagamento;
    private String observacoes;
    private final GerenteDTO gerente;
//    private final LocatarioDTO locatario;
//    private final MaquinaDTO maquina;

    // Construtor da classe Contrato
    public ContratosDTO(long id,
            //LocatarioDTO locatario,
            LocalDate dataInicio, LocalDate dataExpiracao, LocalDate dataPagamento,
            String observacoes) {
        
        this.id = id;
        this.dataExpiracao = dataExpiracao;
        this.dataInicio = dataInicio;
        this.dataPagamento = dataPagamento;
        this.observacoes = observacoes;
//        this.locatario = locatario Depois fazer verificação melhor disso

        IGerenteDAO daoGerente = new GerenteDAO();
        this.gerente = daoGerente.get();
        
    }
    
    public ContratosDTO(//LocatarioDTO locatario,
            LocalDate dataInicio, LocalDate dataExpiracao, LocalDate dataPagamento,
            String observacoes) {
        
        this.dataExpiracao = dataExpiracao;
        this.dataInicio = dataInicio;
        this.dataPagamento = dataPagamento;
        this.observacoes = observacoes;
//        this.locatario = locatario Depois fazer verificação melhor disso

        IGerenteDAO daoGerente = new GerenteDAO();
        this.gerente = daoGerente.get();
        
    }
    
    public long getId() {
        return id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }
    
    public double getValorPagamento() {
        return valor;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public GerenteDTO getGerente() {
        return gerente;
    }
    
    public String getGerenteNome() {
        return "oi";
    }
    
    public String getGerenteCPF() {
        return "oi";
    }
    
    public String getGerenteTelefone() {
        return "oi";
    }
    
    public String getGerenteEmail() {
        return "oi";
    }

    /*
    public Locatario getLocatario() {
        return locatario;
    }
*/
    
    public String getLocatarioNome() {
        return "sla";
    }
    
    public String getLocatarioCPF() {
        return "sla";
    }
    
    public String getLocatarioTelefone() {
        return "sla";
    }
    
    public String getLocatarioEmail() {
        return "sla";
    }
    
    public void setId(long id) {
        // esperando BD, talvez seja bom colocar um id para cada contrato.
    }
    
    @Override
    public String toString() {
        return "Contrato 1, inicio em" + dataInicio.toString();
    }
}