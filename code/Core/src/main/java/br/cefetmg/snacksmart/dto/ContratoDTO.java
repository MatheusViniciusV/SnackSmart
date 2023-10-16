/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.snacksmart.dto;

import java.util.Calendar;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.dao.GerenteDAO;

/**
 *
 * @author eloym
 */
public class ContratoDTO {
    private long id;
    private Calendar dataInicio;
    private Calendar dataExpiracao;
    private Calendar dataPagamento;
    private String observacoes;
    private final GerenteDTO gerente;
//    private final LocatarioDTO locatario;

    // Construtor da classe Contrato
    ContratoDTO(long id,
            //LocatarioDTO locatario,
            Calendar dataInicio, Calendar dataExpiracao, Calendar dataPagamento,
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
    
    ContratoDTO(//LocatarioDTO locatario,
            Calendar dataInicio, Calendar dataExpiracao, Calendar dataPagamento,
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

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public Calendar getDataExpiracao() {
        return dataExpiracao;
    }

    public Calendar getDataPagamento() {
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
}