<%-- 
    Document   : GER_financeiro
    Created on : 3 de out. de 2023, 11:00:04
    Author     : VictorN77
--%>

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
            <input type="button" id="botaoRelatorio" onClick ="document.location.href = '/locadora/controller?pagina=EmitirRelatorio'" value="Emitir relatório geral"></input>
        </div>

        <div class="conteudo">
            <div class="box">
                <!-- <%String dados[] = (String[])request.getAttribute("dados");%>a -->
                <p>Recebimento previsto para o mês:</p>
                <p id="recebimento">123.45</p>
            </div>
            <div class="box">
                <p>Gastos feitos neste mês:</p>
                <p id="gastos">12.34</p>

            </div>
            <div class="box">
                <p>Saldo total</p>
                <p id="saldo">111.11</p>


            </div>
        </div>
        <br>
        <div class="conteudo">
                    <%@include file="comuns/Calendario.jsp" %>
            </div>
    </body>

</html>
