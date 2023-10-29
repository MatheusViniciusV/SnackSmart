<%-- 
    Document   : FinanceiroGerente
    Created on : 24 de out. de 2023, 09:32:49
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/logotipo-removebg-preview.png" type="image/png">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/Calendario.css">
        <title>Financeiro</title>

    </head>
    <body>
       <%@include file="comuns/retornarInicial.jsp" %>
        <div class="div-head">
            <h1>Financeiro</h1>
            <div id="emitirRelatorio"><a href='/locadora/controller?pagina=EmitirRelatorio'>Emitir Relatorio</a></div>
        </div>
    <div class="div-principal">
        <div class="div-segundo">
            <h2>Recebimento Previsto</h2>
            <p>R$ 1.000,00</p>
        </div>
        
        <div class="div-segundo">
            <h2>Gasto do Mês</h2>
            <p>R$ 800,00</p>
        </div>
        
        <div class="div-segundo">
            <h2>Lucro</h2>
            <p>R$ 200,00</p>
        </div>
    </div>
        <%@include file="comuns/Calendario.jsp" %>
    </body>
</html>
