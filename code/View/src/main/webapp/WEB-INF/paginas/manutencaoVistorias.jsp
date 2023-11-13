<%-- 
    Document   : manutençãovistorias
    Created on : 11 de nov. de 2023, 12:44:58
    Author     : marco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.dto.FeedbackDTO" %>
<%@page import="br.cefetmg.snacksmart.dto.MaquinaDTO" %>
<%@page import="java.util.ArrayList" %>
<%@include file="../../comuns/JSTL.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <title>Manuteção Vistorias</title>
        <%@include file="../../comuns/jqueryLink.jsp" %>
    </head>
    <body>
        <%@include file="../../comuns/retornarInicial.jsp" %>
        <h1>Manuteção Vistorias</h1>
         <h1>Manuteção Vistorias</h1>
        <div id="menu">
           <input type="text" id="Pesquisa" name="Pesquisa" required>
           <button id="enviar">↻</button>
           <div id="ListaMaquinas"></div>
        </div>
        <article id="informacoes">
            <h1 id="nomeMaquina"></h1>
            <h1 id="codeMaquina"></h1>
            <div class="botoes">
                <a></a>
                <a></a>
            </div>
        </article>
        <script>
            var maquinasArray = [
                <c:forEach items="${vetorMaquinas}" var="maquina">
                    {
                        nome: '${maquina.nome}',
                        codigo: '${maquina.codigo}',
                        locatario: '${maquina.locatario.nome}'
                    },
                </c:forEach>
            ];
            criarSlotMaquina(maquinasArray);
        </script>
        <script src="../../js/manutencaoVistorias.js"></script> 
        <%@include file="../../comuns/Calendario.jsp" %>
    </body>
</html>
