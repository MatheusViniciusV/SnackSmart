<%-- 
    Document   : GER_financeiro
    Created on : 3 de out. de 2023, 11:00:04
    Author     : VictorN77
--%>
<%@ page import= "br.cefetmg.snacksmart.dao.ContratosDAO" %>
<%@ page import= "br.cefetmg.snacksmart.dto.ContratoDTO" %>
<%@ page import= "br.cefetmg.snacksmart.dto.MaquinaDTO" %>
<%@ page import= "br.cefetmg.snacksmart.dao.MaquinaDAO" %>
<%@ page import= "br.cefetmg.snacksmart.dto.VistoriaDTO" %>
<%@ page import= "br.cefetmg.snacksmart.dao.VistoriaDAO" %>

<%@ page import= "java.util.ArrayList" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/financeiro.css">
        <link rel="stylesheet" href="css/Calendario.css">


        <title>JSP Page</title>
    </head>

    <body>
        <%@include file="comuns/retornarInicial.jsp" %>
        <div id="topo">
            <h1 id ="titulo">Finaceiro</h1>
            <input type="button" id="botaoRelatorio" onClick ="document.location.href = '/locadora/controller?pagina=EmitirRelatorio'" value="Emitir relat?rio geral"></input>
        </div>

        <div class="conteudo">
            <div class="box">
                <%
                double recebimento = 0.00, saldo, gasto = 0.00;

            ContratosDAO Contratos = new ContratosDAO();
            try {
                ArrayList<ContratoDTO> listaContratos = Contratos.listaTodos();

                for (ContratoDTO i : listaContratos) {

                    recebimento += i.getValorPagamento();

                }
            } catch (Exception a) {
            }
            VistoriaDAO Vistorias = new VistoriaDAO();
            
            try{
            
                ArrayList<VistoriaDTO> listaVistorias = Vistorias.listarTodas();
                
                for (VistoriaDTO v : listaVistorias){
                
                   // gasto += v.getValor();
                }
            }
            catch(Exception e){}
            

            saldo = recebimento - gasto;
%>
                <p>Recebimento previsto para o mês:</p>
                <p id="recebimento">R$: <%= Double.toString(recebimento)%></p>
            </div>
            <div class="box">
                <p>Gastos feitos neste mês:</p>
                <p id="gastos">R$: <%= Double.toString(gasto)%></p>

            </div>
            <div class="box">
                <p>Saldo total</p>
                <p id="saldo">R$: <%= Double.toString(saldo)%></p>


            </div>
        </div>
        <br>
        <div class="conteudo">
                    <%@include file="comuns/Calendario.jsp" %>
            </div>
    </body>

</html>