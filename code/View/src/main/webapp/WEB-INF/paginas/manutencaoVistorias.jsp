<%-- 
    Document   : manutençãovistorias
    Created on : 11 de nov. de 2023, 12:44:58
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
        <link rel="stylesheet" href="css/manutencao.css">
        <link rel="stylesheet" href="css/Calendario.css">    
        <title>Manuteção e Vistorias</title>
        
    </head>
    <body>      
        <%@include file="../../comuns/retornarInicial.jsp" %>
        <main id="manutencaoMain">     
            <h1 id="tituloDaPagina">Manuteção e Vistorias</h1>   
            <div id="bloquearConteudo"></div>
            <div id="pesquisarMaquina" class="slot">
                <input type="text" placeholder="Procurar por máquina" id="busca"></input>
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
                    var vetorManutencaoArray = [
                        <c:forEach items="${vetorFeedbackManutencao}" var="feedback">
                            {
                                titulo: '${feedback.titulo}',
                                mensagem: '${feedback.mensagem}',
                                codigo: '${feedback.codigo}',
                                tipo: '${feedback.getTipoFeedback()}'
                            },
                        </c:forEach>
                    ];
                </script>             
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

            <div id="notificacoes" class="slot">
                <h1>Manutenções Solicitadas</h1>
            </div>
        </main>
            
        <article id="formVistoria">
            <form action="GerenciarManutencaoVistoria" method="post">
                <label id="descricaoInput">Descrição:</label>
                <input id="descricao" type="text" name="descricao">
                
                <label id="dataInput">Data:</label>
                <input id="data" type="date" name="data">
                
                <input id="enviar" type="submit">    
            </form>
            <button id="cancelar">Cancelar</button>
        </article>    
            
        <article id="feedbackRetornado">
            <button class="cancelarFeedback">Cancelar</button>
        </article>
        <script src="js/manutencaoVistorias.js"></script>
        <!-- Cria os slots das máquinas -->
        <c:forEach var="item" items="${vetorMaquinas}">
            <script>
                try {
                    criarSlotMaquina("${item.nome}", "${item.codigo}", "${item.getUrlImagem()}");
                } catch (erro) {
                    console.log("Erro ao criar as máquinas: " + erro);
                }
            </script>  
        </c:forEach>
        <c:forEach var="item" items="${vetorFeedbackManutencao}">
            <script>
                try {
                    criarNotificacao("${item.codigo}", "${item.titulo}", "${item.mensagem}");
                } catch (erro) {
                    console.log("Erro ao criar as máquinas: " + erro);
                }
            </script>  
        </c:forEach>
    </body>
</html>
