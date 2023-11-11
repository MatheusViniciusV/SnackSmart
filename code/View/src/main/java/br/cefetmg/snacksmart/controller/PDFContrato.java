package br.cefetmg.snacksmart.controller;

import java.io.IOException;

import br.cefetmg.snacksmart.dto.ContratoDTO;
import br.cefetmg.snacksmart.dto.GerenteDTO;
import br.cefetmg.snacksmart.dto.LocatarioDTO;
import br.cefetmg.snacksmart.exceptions.bd.PersistenciaException;
import br.cefetmg.snacksmart.services.gerente.ManterContratos;
import br.cefetmg.snacksmart.utils.enums.StatusContrato;
import br.cefetmg.snacksmart.utils.enums.TipoUsuario;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import br.cefetmg.snacksmart.exceptions.dao.ElementoNaoExisteException;
import br.cefetmg.snacksmart.exceptions.dao.LocatarioInvalidoException;
import br.cefetmg.snacksmart.services.locatario.AcessarContratos;
import jakarta.servlet.http.HttpSession;

import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author eloym
 */
@WebServlet(name="PDFContrato", urlPatterns={"/PDFContrato"})
public class PDFContrato extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        HttpSession session = request.getSession();
        TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
        int id =  Integer.parseInt(request.getParameter("id"));

        try {
            if (tipoUsuario == TipoUsuario.LOCADOR) {
                ManterContratos acesso = new ManterContratos();

                ContratoDTO contrato = acesso.getContrato(id);

                if (contrato == null)
                    throw new ElementoNaoExisteException("Contrato " + id + " não existe.");

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=contrato-" + id + ".pdf");
                getPdf(contrato, response.getOutputStream());
            } else if (tipoUsuario == TipoUsuario.LOCATARIO) {
                LocatarioDTO locatario = (LocatarioDTO) session.getAttribute("usuario");

                AcessarContratos acessoContratos = new AcessarContratos(request.getParameter("locatarioCPF"));
                ContratoDTO contrato = acessoContratos.getContrato(id);

                if (contrato == null)
                    throw new LocatarioInvalidoException("Locatario de cpf " + locatario.getCPF() + " não tem acesso ao contrato ou não existe.");

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=contrato-" + id + ".pdf");
                getPdf(contrato, response.getOutputStream());
            }
        } catch(ElementoNaoExisteException | LocatarioInvalidoException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch(DocumentException | IOException | SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro Interno.\n" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PDFContrato.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPdf(ContratoDTO contrato, OutputStream output)
            throws ElementoNaoExisteException, LocatarioInvalidoException, DocumentException, IOException,
            SQLException, ClassNotFoundException, PersistenciaException {

        LocatarioDTO locatario = contrato.getLocatario();
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
