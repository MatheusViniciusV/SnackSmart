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
            <div id="emitirRelatorio"><a href="RelatorioGerente.jsp">Emitir Relatório Geral</a></div>
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
       <%--  <div class="conteudo">
            <div class="box">
                <%String dados[] = (String[])request.getAttribute("dados"); %>
                <p>Recebimento previsto para o mês:</p>
                <p id="recebimento"><%= dados[0]%></p>
                <p>Data de recebimento: <%= dados[1]%></p>
            </div>
            <div class="box">
                <p>Gastos feitos neste mês:</p>
                <p id="gastos">R$: <%= dados[2]%></p>

            </div>
            <div class="box">
                <p>Saldo total</p>
                <p id="saldo">R$: <%= dados[3]%></p>
            </div>
        </div>
            --%> <!-- comment -->
        <br>
        <%@include file="comuns/Calendario.jsp" %>
    </body>

</html>