package br.cefetmg.snacksmart.dto;

import java.time.LocalDate;
import br.cefetmg.snacksmart.idao.IGerenteDAO;
import br.cefetmg.snacksmart.dao.GerenteDAO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.utils.DataManager;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author eloym
 */
public class ContratoDTO {
    private int id;
    private Double valor;
    private StatusContrato status;
    private DataManager dataInicio, dataExpiracao, dataPagamento;
    private String observacoes;
    private GerenteDTO gerente;
    private LocatarioDTO locatario;
    private MaquinaDTO maquina;

    public ContratoDTO(int id,
                       double valor,
                       LocatarioDTO locatario,
                       MaquinaDTO maquina,
                       DataManager dataInicio, DataManager dataExpiracao, DataManager dataPagamento,
                       String observacoes,
                       StatusContrato status) throws PersistenciaException {

        this.id = id;
        this.valor = valor;
        this.dataExpiracao = dataExpiracao;
        this.dataInicio = dataInicio;
        this.dataPagamento = dataPagamento;
        this.observacoes = observacoes;
        this.locatario = locatario;
        this.maquina = maquina;
        this.status = status;

        IGerenteDAO daoGerente = new GerenteDAO();
        this.gerente = daoGerente.get();

    }
    
    public ContratoDTO(double valor,
                       GerenteDTO gerente,
                       LocatarioDTO locatario,
                       MaquinaDTO maquina,
                       DataManager dataInicio, DataManager dataExpiracao, DataManager dataPagamento,
                       String observacoes) {
        
        this.dataExpiracao = dataExpiracao;
        this.dataInicio = dataInicio;
        this.dataPagamento = dataPagamento;
        this.observacoes = observacoes;
        this.valor = valor;
        this.locatario = locatario;
        this.gerente = gerente;
        this.maquina = maquina;
        status = StatusContrato.VIGENTE;


    }
    
    public int getId() {
        return id;
    }
    
    public StatusContrato getStatus() {
        return status;
    }

    public DataManager getDataInicio() {
        return dataInicio;
    }

    public DataManager getDataFim() {
        return dataExpiracao;
    }

    public DataManager getDataPagamento() {
        return dataPagamento;
    }
    
    public double getValorPagamento() {
        return valor;
    }

    public String printValorPagamento() {
        return String.format("R$ %.2f", valor);
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


    public LocatarioDTO getLocatario() {
        return locatario;
    }

    public void setGerente(GerenteDTO gerente) {
        this.gerente = gerente;
    }

    @Override
    public String toString() {
        return String.format("Contrato %d, iniciado na data de %s com fim em %s",
                id, dataInicio, dataExpiracao);
    }

    public String toJson() {
        String forma = """
        {
            "contrato":{
                "id":"%d",
                "observacoes":"%s",
                "dataInicio":"%s",
                "dataFim":"%s",
                "diaPagamento":"%s",
                "valor":%.2f,
                "locatario":"%s",
                "maquina":"",
                "estado":"%s"
            }
        }
        """;

        return String.format(forma,
                id,
                observacoes,
                dataInicio,
                dataExpiracao,
                dataPagamento.getDia(),
                valor,
                locatario,
                status);
    }
}