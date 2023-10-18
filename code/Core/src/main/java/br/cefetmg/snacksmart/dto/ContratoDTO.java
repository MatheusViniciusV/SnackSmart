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
public class ContratoDTO {
    private long id;
    private LocalDate dataInicio;
    private LocalDate dataExpiracao;
    private LocalDate dataPagamento;
    private String observacoes;
    private final GerenteDTO gerente;
//    private final LocatarioDTO locatario;

    // Construtor da classe Contrato
    ContratoDTO(long id,
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
    
    public ContratoDTO( LocalDate dataInicio, LocalDate dataExpiracao, LocalDate dataPagamento, String observacoes) {
        
        this.dataExpiracao = dataExpiracao;
        this.dataInicio = dataInicio;
        this.dataPagamento = dataPagamento;
        this.observacoes = observacoes;
//        this.locatario = locatario Depois fazer verificação melhor disso

        IGerenteDAO daoGerente = new GerenteDAO();
        this.gerente = daoGerente.get();
        
    }
    //LocatarioDTO locatario,
        
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

    public String getObservacoes() {
        return observacoes;
    }

    public GerenteDTO getGerente() {
        return gerente;
    }

    /*
    public Locatario getLocatario() {
        return locatario;
    }
*/
    
    public void setId(long id) {
        // esperando BD, talvez seja bom colocar um id para cada contrato.
    }
    
    @Override
    public String toString() {
        return String.format("Contrato:\n data inicio: %s", dataInicio.toString());
    }
}