package br.cefetmg.snacksmart.services.locatario;

import br.cefetmg.snacksmart.dao.ContratosDAO;
import br.cefetmg.snacksmart.dao.LocatarioDAO;
import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.exceptions.dao.ElementoNaoExisteException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.idao.IContratosDAO;
import br.cefetmg.snacksmart.idao.ILocatarioDAO;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;
import br.cefetmg.snacksmart.utils.enums.TiposOrdenacaoContrato;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eloym
 */
public class AcessarContratos {
    private IContratosDAO dao;
    private ILocatarioDAO daoLocatario;
    private LocatarioDTO locatario;
    
    public AcessarContratos(String locatarioCPF) throws PersistenciaException {
        dao = new ContratosDAO();
        daoLocatario = new LocatarioDAO();
        locatario = daoLocatario.consultarPorCPF(locatarioCPF);
    }

    public AcessarContratos(LocatarioDTO locatario) {
        dao = new ContratosDAO();
        daoLocatario = new LocatarioDAO();
        this.locatario = locatario;
    }
    
    public void solicitarCancelamento(int id)
            throws ClassNotFoundException, SQLException, ElementoNaoExisteException, LocatarioInvalidoException, ClassNotFoundException {
        if(dao.consultarPorIdLocatario(id, locatario) != null)
            dao.atualizarStatus(id, StatusContrato.CANCELAMENTO_SOLICITADO);
        else
            throw new LocatarioInvalidoException("Locatario " + locatario.getNome() + "não existe ou não tem acesso ao contrato");
    }
    
    public ContratoDTO getContrato(int id)
            throws SQLException, ElementoNaoExisteException, LocatarioInvalidoException {
        ContratoDTO contrato;
        
        contrato = dao.consultarPorIdLocatario(id, locatario);

        if(contrato == null)
            throw new LocatarioInvalidoException("Locatario " + locatario.getNome() + "não existe ou não tem acesso ao contrato");
        
        return contrato;
    }
    
    public ArrayList<ContratoDTO> getContratos(StatusContrato status) throws LocatarioInvalidoException, SQLException {
        ArrayList<ContratoDTO> contratos;

        if(status != null) {
            contratos = dao.filtra(locatario, status, TiposOrdenacaoContrato.MENOR_ID);
        } else {
            contratos = dao.filtra(locatario, TiposOrdenacaoContrato.MENOR_ID);
        }

        return contratos;
    }

    public ArrayList<ContratoDTO> getContratos(TiposOrdenacaoContrato ordenacao) throws LocatarioInvalidoException, SQLException {
        return dao.filtra(locatario, ordenacao);
    }

    public ArrayList<ContratoDTO> getContratos(StatusContrato status, TiposOrdenacaoContrato ordenacao) throws LocatarioInvalidoException, SQLException {
        ArrayList<ContratoDTO> contratos;

        if(status != null) {
            contratos = dao.filtra(locatario, status, ordenacao);
        } else {
            contratos = dao.filtra(locatario, ordenacao);
        }

        return contratos;
    }
}
