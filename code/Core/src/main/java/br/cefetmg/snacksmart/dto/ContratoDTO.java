package br.cefetmg.snacksmart.dto;

import java.time.LocalDate;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.dao.GerenteDAO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author eloym
 */
public class ContratoDTO {
    private long id;
    private double valor;
    private LocalDate dataInicio;
    private LocalDate dataExpiracao;
    private LocalDate dataPagamento;
    private String observacoes;
    private GerenteDTO gerente;
    private LocatarioDTO locatario;
    private MaquinaDTO maquina;

    // Construtor da classe Contrato
    public ContratoDTO(long id,
            double valor,
            LocatarioDTO locatario,
            MaquinaDTO maquina,
            LocalDate dataInicio, LocalDate dataExpiracao, LocalDate dataPagamento,
            String observacoes) throws PersistenciaException {
        
        this.id = id;
        this.valor = valor;
        this.dataExpiracao = dataExpiracao;
        this.dataInicio = dataInicio;
        this.dataPagamento = dataPagamento;
        this.observacoes = observacoes;
        this.locatario = locatario;

        IGerenteDAO daoGerente = new GerenteDAO();
        this.gerente = daoGerente.get();
        
    }
    
    public ContratoDTO(//LocatarioDTO locatario,
            LocalDate dataInicio, LocalDate dataExpiracao, LocalDate dataPagamento,
            String observacoes) throws PersistenciaException {
        
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
    
    public StatusContrato getStatus() {
        return StatusContrato.INVALIDO;
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
    
    public MaquinaDTO getMaquina() {
        return maquina;
    }
    
//    public String getGerenteNome() {
//        return "oi";
//    }
//    
//    public String getGerenteCPF() {
//        return "oi";
//    }
//    
//    public String getGerenteTelefone() {
//        return "oi";
//    }
//    
//    public String getGerenteEmail() {
//        return "oi";
//    }

    /*
    public Locatario getLocatario() {
        return locatario;
    }
*/
    
//    public String getLocatarioNome() {
//        return "sla";
//    }
//    
//    public String getLocatarioCPF() {
//        return "sla";
//    }
//    
//    public String getLocatarioTelefone() {
//        return "sla";
//    }
//    
//    public String getLocatarioEmail() {
//        return "sla";
//    }

    public void setId(long id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formata = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        String str = String.format("Contrato %d, iniciado na data de %s com fim em %s",
                id, dataInicio.format(formata), dataExpiracao.format(formata));
        
        return str;
    }
}