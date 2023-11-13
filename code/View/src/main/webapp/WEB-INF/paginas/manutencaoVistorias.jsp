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
        <%
            ArrayList<MaquinaDTO> listaMaquinas = (ArrayList<MaquinaDTO>) request.getAttribute("vetorMaquinas");
            
            Gson gson = new Gson();
            String jsonMaquinas = gson.toJson(listaMaquinas);
        %>
        <script>
            criarSlotMaquina(<%= jsonMaquinas %>);
        </script>
        <div id="menu">
           <input type="text" id="termoPesquisa" name="Pesquisa" required>
        </div>
         <script src="js/manutecaoVistorias.js"></script> 
    </body>
</html>
