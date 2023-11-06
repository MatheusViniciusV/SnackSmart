<%-- 
    Document   : GER_financeiro
    Created on : 3 de out. de 2023, 11:00:04
    Author     : VictorN77
--%>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/img/logotipo-removebg-preview.png" type="image/png">
        <link rel="stylesheet" href="css/financeiro.css">
        <link rel="stylesheet" href="css/Calendario.css">
        
        <title>Financeiro</title>
    </head>

    <body>
        <%@include file="comuns/retornarInicial.jsp" %>
        <div class="div-head">
            <h1>Financeiro</h1>
            <div id="emitirRelatorio"><a href="RelatorioGerente.jsp">Emitir Relatorio Geral</a></div>
        </div>
    <div class="div-principal">
            <div class="div-segundo">
                <%String dados[] = (String[])request.getAttribute("dados"); %>
                <p>Recebimento previsto para o m�s:</p>
                <p id="recebimento"><%= dados[0]%></p>
                <p>Data de recebimento: <%= dados[1]%></p>
            </div>
            <div class="div-segundo">
                <p>Gastos feitos neste mês:</p>
                <p id="gastos">12.34</p>

            </div>
            <div class="div-segundo">
                <p>Saldo total</p>
                <p id="saldo">R$: <%= dados[3]%></p>
            </div>
        </div>
        <br>
        <%@include file="comuns/Calendario.jsp" %>
    </body>

</html>
