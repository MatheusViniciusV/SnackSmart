package br.cefetmg.snacksmart.service_locatario;

import br.cefetmg.snacksmart.dao.ContratosDAO;
import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.dao.ElementoNaoExisteException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.idao.IContratosDAO;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;
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
    
    public AcessarContratos() {
        dao = new ContratosDAO();
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
        LocatarioDTO locatario = new LocatarioDTO();
        
        ArrayList contratos = dao.filtra(locatario);
        
        return contratos;
    }
    
    public void getPdf(long id, String locatarioCPF, OutputStream output)
            throws ElementoNaoExisteException, LocatarioInvalidoException, DocumentException, IOException, SQLException, ClassNotFoundException {
//        LocatarioDTO locatario = new LocatarioDTO();
        
//        if(dao.getIdLocatario(id, locatarioCPF) == null) {
//            throw new LocatarioInvalidoException("Locatario " + locatarioCPF + "não existe ou não tem acesso ao contrato");
//        }
        
        Document documento = new Document(PageSize.A4, 60, 60, 40, 40);
        
        PdfWriter.getInstance(documento, output);
//        ContratoDTO contrato = dao.getId(id);

        BaseFont fontBase = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        Font fontTitulo = new Font(fontBase, 20, Font.BOLD);
        Font fontParagrafo = new Font(fontBase, 12, Font.NORMAL);
        Paragraph linhaVazia = new Paragraph(" ", new Font(fontBase, 8, Font.NORMAL));

        documento.open();

        documento.add(new Paragraph("Contrato ", fontTitulo));

        String[] textos = new String[3];

//        GerenteDTO gerente = contrato.getGerente();
//        LocatarioDTO locatario = contrato.getLocatario();
        
        textos[0] = String.format(""
                + "Contrato firmado entre o locador %s, de CPF %s, número de telefone %s e email "
                + "%s, atuais até o momento de edição deste contrado, e o locatário %s, de CPF %s"
                + ", número de telefone %s e email %s, atuais até o momento de edição deste contrado,"
                + " sobre o direito de uso da máquina de vendas de produtos alimenticios indentificada "
                + "pelo código.", 
                "teste",
                "teste",
                "teste",
                "teste",
                "teste",
                "teste",
                "teste",
                "teste");
//                gerente.getNome(),
//                gerente.getCPF(),
//                gerente.getTelefone(),
//                gerente.getEmail(),
//                contrato.getLocatarioNome(),
//                contrato.getLocatarioCPF(),
//                contrato.getGerenteTelefone(),
//                contrato.getLocatarioEmail());

        textos[1] = String.format(""
                + "A validade deste contrato se estende da data %s a data %s, sendo um periodo de %d"
                + " meses. A locação terá um valor mensal de %2f, a ser pago pelo locatário a partir"
                + " do dia %s de cada mês durante a válidade do contrato, havendo um prazo de 7 dias"
                + " uteis após o dia de pagamento estipulado para que seja efetuado o pagamento."
                + " Caso o pagamento não seja cumprido poderá haver consequências previstas na "
                + "clausula observações ou até o cancelamento deste contrato.", 
                "teste",
                "teste",
                1,
                1.1,
                "teste");
//                contrato.getDataInicio(),
//                contrato.getDataExpiracao(),
//                364,
//                contrato.getValorPagamento(),
//                contrato.getDataPagamento());

        textos[2] = ""
                + "É de responsabilidade do locatários zelar pelo estado da máquina, evitando ambientes"
                + " que possam estragar, protege-la de vandalismos e furtos, em caso de danos causados "
                + "por falta de monitoramento e/ou negligência do locatario, ele deve restituir a maquina ou"
                + "seu valor de mercado. É de responsabilidade do locador prestar manutenção para eventuais erros"
                + " de funcionamento da máquina em até 24 horas, em caso de recolha da máquina deverá substituir"
                + " ela, havendo desconto de 10%% do valor do aluguel mensal para cada dia de atraso ao resolver a"
                + " situação.";


//        textos[3] = (contrato.getObservacoes().length() > 0) ? "Para concluir, cabe as seguintes informações" + contrato.getObservacoes() : "";



        Paragraph[] paragrafos = new Paragraph[3];
        for(int i = 0; i < paragrafos.length; i++) {
            paragrafos[i] = new Paragraph(textos[i], fontParagrafo);
            paragrafos[i].setFirstLineIndent(30);
            documento.add(linhaVazia);
            documento.add(paragrafos[i]);
        }


        Paragraph espacoAssinaturaLocador = new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n"
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
