package br.cefetmg.snacksmart.service_locatario;

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
    
    public AcessarContratos() {
        dao = new ContratosDAO();
        daoLocatario = new LocatarioDAO();
    }
    
    public void solicitarCancelamento(int id, String locatarioCPF)
            throws ClassNotFoundException, SQLException, ElementoNaoExisteException, LocatarioInvalidoException, ClassNotFoundException {
        LocatarioDTO locatario = new LocatarioDTO();
        
        if(dao.consultarPorIdLocatario(id, locatario) != null)
            dao.atualizarStatus(id, StatusContrato.CANCELAMENTO_SOLICITADO);
        else
            throw new LocatarioInvalidoException("Locatario " + locatario.getNome() + "não existe ou não tem acesso ao contrato");
    }
    
    public ContratoDTO getContrato(int id, String locatarioCPF)
            throws SQLException, ElementoNaoExisteException, LocatarioInvalidoException, ClassNotFoundException {
        ContratoDTO contrato;
        
        LocatarioDTO locatario = new LocatarioDTO();
        
        if(dao.consultarPorIdLocatario(id, locatario) != null)
            contrato = dao.consultarPorId(id);
        else
            throw new LocatarioInvalidoException("Locatario " + locatario.getNome() + "não existe ou não tem acesso ao contrato");
        
        return contrato;
    }
    
    public ArrayList<ContratoDTO> getContratos(String locatarioCPF) throws LocatarioInvalidoException, SQLException {
        LocatarioDAO locatarioDAO = new LocatarioDAO();
        LocatarioDTO locatario = null;
        try {
            locatario = locatarioDAO.consultarPorCPF(locatarioCPF);
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }

        return dao.filtra(locatario, TiposOrdenacaoContrato.MENOR_ID);
    }
    
    public void getPdf(int id, String locatarioCPF, OutputStream output)
            throws ElementoNaoExisteException, LocatarioInvalidoException, DocumentException, IOException,
                    SQLException, ClassNotFoundException, PersistenciaException {
        LocatarioDTO locatario = daoLocatario.consultarPorCPF(locatarioCPF);
        ContratoDTO contrato = dao.consultarPorIdLocatario(id, locatario);

        if(contrato == null)
            throw new LocatarioInvalidoException("Locatario de cpf " + locatarioCPF + " não tem acesso ao contrato ou não existe.");

        GerenteDTO gerente = contrato.getGerente();

        Document documento = new Document(PageSize.A4, 60, 60, 40, 40);

        PdfWriter.getInstance(documento, output);

        BaseFont fontBase = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        Font fontTitulo = new Font(fontBase, 20, Font.BOLD);
        Font fontParagrafo = new Font(fontBase, 12, Font.NORMAL);
        Paragraph linhaVazia = new Paragraph(" ", new Font(fontBase, 8, Font.NORMAL));

        documento.open();

        String titulo = String.format("Contrato %d", contrato.getId());
        if(contrato.getStatus() != StatusContrato.VIGENTE) {
            titulo += " " + contrato.getStatus().toString();
        }

        documento.add(new Paragraph(titulo, fontTitulo));

        String[] textos = new String[4];
        
        textos[0] = String.format(""
                + "Contrato firmado entre o locador %s, de CPF %s, número de telefone %s e email "
                + "%s, atuais até o momento de edição deste contrado, e o locatário %s, de CPF %s"
                + ", número de telefone %s e email %s, atuais até o momento de edição deste contrado,"
                + " sobre o direito de uso da máquina de vendas de produtos alimenticios indentificada "
                + "pelo código.",
                gerente.getNome(),
                gerente.getCPF(),
                gerente.getTelefone(),
                gerente.getEmail(),
                locatario.getNome(),
                locatario.getCPF(),
                locatario.getTelefone(),
                locatario.getEmail());

        textos[1] = String.format(""
                + "A validade deste contrato se estende da data %s a data %s, sendo um periodo de %d"
                + " meses. A locação terá um valor mensal de %2f, a ser pago pelo locatário a partir"
                + " do dia %d de cada mês durante a válidade do contrato, havendo um prazo de 7 dias"
                + " uteis após o dia de pagamento estipulado para que seja efetuado o pagamento."
                + " Caso o pagamento não seja cumprido poderá haver consequências previstas na "
                + "clausula observações ou até o cancelamento deste contrato.",
                contrato.getDataInicio(),
                contrato.getDataFim(),
                contrato.getDataFim().diferencaMeses(contrato.getDataInicio()),
                contrato.getValorPagamento(),
                contrato.getDataPagamento().getDia());

        textos[2] = ""
                + "É de responsabilidade do locatários zelar pelo estado da máquina, evitando ambientes"
                + " que possam estragar, protege-la de vandalismos e furtos, em caso de danos causados "
                + "por falta de monitoramento e/ou negligência do locatario, ele deve restituir a maquina ou"
                + "seu valor de mercado. É de responsabilidade do locador prestar manutenção para eventuais erros"
                + " de funcionamento da máquina em até 24 horas, em caso de recolha da máquina deverá substituir"
                + " ela, havendo desconto de 10% do valor do aluguel mensal para cada dia de atraso ao resolver a"
                + " situação.";


        textos[3] = (!contrato.getObservacoes().isEmpty()) ? "Para concluir, cabe as seguintes informações: " + contrato.getObservacoes() : "";



        Paragraph[] paragrafos = new Paragraph[4];
        for(int i = 0; i < paragrafos.length; i++) {
            paragrafos[i] = new Paragraph(textos[i], fontParagrafo);
            paragrafos[i].setFirstLineIndent(30);
            documento.add(linhaVazia);
            documento.add(paragrafos[i]);
        }


        Paragraph espacoAssinaturaLocador = new Paragraph("\n\n\n\n\n\n\n\n\n\n"
                + "Assinatura locador: ______________________________________", fontParagrafo);
        espacoAssinaturaLocador.setAlignment(Element.ALIGN_BOTTOM);
        documento.add(espacoAssinaturaLocador);

        Paragraph espacoAssinaturaLocatario = new Paragraph("\nAssinatura locatario: ______________________________________", fontParagrafo);
        espacoAssinaturaLocatario.setAlignment(Element.ALIGN_BOTTOM);
        documento.add(espacoAssinaturaLocatario);

        Paragraph espacoAssinaturaData = new Paragraph("\nData: ___/___/_____", fontParagrafo);
        espacoAssinaturaData.setAlignment(Element.ALIGN_BOTTOM);
        documento.add(espacoAssinaturaData);
        documento.close();
    }
}
