<%-- 
    Document   : gestaoFinaceirioLocatario
    Created on : 27 de nov. de 2023, 16:22:47
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.dto.MaquinaDTO" %>
<%@page import="br.cefetmg.snacksmart.dao.MaquinaDAO" %>
<%@page import="java.util.ArrayList" %>
<%@include file="../../comuns/JSTL.jsp" %>
<%@include file="../../comuns/jqueryLink.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">      
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/Calendario.css">
        <link rel="stylesheet" href="css/gestaoFinanceiroLocatario.css">
        <title>Financeiro</title>
    </head>
    <body>
        <%@include file="../../comuns/retornarInicial.jsp" %>
        <h1>Financeiro</h1>
         <% 
                Double faturamento = (Double) request.getAttribute("faturamento");
                Double gastos = (Double) request.getAttribute("gastos");
                Double saldo = (Double) request.getAttribute("saldo");
         %>  
         <div class="div-principal">
            <div id="Faturamento" class="div-segundo">
                <p>Faturamento previsto para o mês</p>
                <p id="valorFaturamento"><%= faturamento %></p>
            </div>
            <div id="Gastos" class="div-segundo">
                <p>Gastos feitos esse mês</p>
                <p id="valorGastos"><%= gastos %></p>
            </div>
            <div id="Saldo" class="div-segundo">
                <p>Saldo total</p>
                <p id="valorSaldo"><%= saldo %></p>
            </div>
        </div>
        <div id="calendario" >
            <%@include file="../../comuns/Calendario.jsp" %>
        </div>   
    </body>
</html>
