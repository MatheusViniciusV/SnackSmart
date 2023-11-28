<%-- 
    Document   : emissaoRelatorio
    Created on : 21 de nov. de 2023, 10:25:05
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
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">      
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/Calendario.css">  
        <title>Emissão de Relatorios</title>
    </head>
    <body>
        <%@include file="../../comuns/retornarInicial.jsp" %>
        <h1>Emissão de Relatorios</h1>
        <div id="menu">
            <input type="text" placeholder="Procurar por máquina" id="busca">
            <div id="resultMaquina" ></div>
        <!-- Cria os vetores das consultas SQL -->
                <script>
                    var vetorMaquinaArray = [
                        <c:forEach items="${vetorMaquinas}" var="maquina">
                            {
                                nome: '${maquina.nome}',
                                codigo: '${maquina.codigo}',
                                locatario: '${maquina.locatario.nome}'
                            },
                        </c:forEach>
                    ];
                    var vetorFeedbackArray = [
                        <c:forEach items="${vetorFeedback}" var="feedback">
                            {
                                titulo: '${feedback.titulo}',
                                mensagem: '${feedback.mensagem}',
                                codigo: '${feedback.codigo}',
                                tipo: '${feedback.getTipoFeedback()}'
                            },
                        </c:forEach>
                    ];
                </script>
                
                <div id="boxInfo" class="slot">
                <img id="imagemMaquina" src="img/NonePhoto.png" alt="">
                <div id="textosMaquina">
                    <p id="nomeMaquina">SELECIONE UMA MÁQUINA</p>
                    <p id="locatarioMaquina"></p>
                </div>
                <input type="button" class="botaoRelatorio" id="botaoAgenda" value="Agendar vistoria">
                <input type="button" class="botaoRelatorio" id="botaoFeedback" value="Verificar Feedbacks">
            </div>
        </div>
        <div id="boxInfo" class="slot">
                <img id="imagemMaquina" src="img/NonePhoto.png" alt="">
                <div id="textosMaquina">
                    <p id="nomeMaquina">SELECIONE UMA MÁQUINA</p>
                    <p id="locatarioMaquina"></p>
                </div>
                <input type="button" class="botaoRelatorio" id="botaoAgenda" value="Agendar vistoria">
                <input type="button" class="botaoRelatorio" id="botaoFeedback" value="Verificar Feedbacks">
            </div>
        <div id="calendario" >
            <%@include file="../../comuns/Calendario.jsp" %>
        </div>   
    </body>
</html>
