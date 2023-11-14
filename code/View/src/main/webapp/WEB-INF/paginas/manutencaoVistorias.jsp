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
        <title>Manuteção Vistorias</title>
        
    </head>
    <body>      
        <%@include file="../../comuns/retornarInicial.jsp" %>
        <main id="manutencaoMain">
            
            <h1 id="tituloDaPagina">Manuteção Vistorias</h1>   
            
            <div id="pesquisarMaquina" class="slot">
                <input type="text" placeholder="procurar máquina" id="busca"></input>
                <div id="resultMaquina" ></div>
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
                    var vetorFeebackArray = [
                        <c:forEach items="${vetorFeedback}" var="feedback">
                            {
                                nome: '${maquina.nome}',
                                codigo: '${maquina.codigo}',
                                locatario: '${maquina.locatario.nome}'
                            },
                        </c:forEach>
                    ];
                </script>
                
            </div>
            <div id="boxInfo" class="slot">
                <img id="imagemMaquina" src="img/NonePhoto.png" alt="">
                <div>
                    <p id="nomeMaquina">Nome da Maquina: -SELECIONE-</p>
                    <p id="locatarioMaquina">Alugada por: -SELECIONE- </p>
                </div>
                <input type="button" class="botaoRelatorio" id="botaoAgenda" onClick ="document.getElementById('formVistoria').display ='none' " value="Agendar vistoria">
                <input type="button" class="botaoRelatorio" id="botaoFeedback" value="Verificar Feedbacks">
            </div>
            <script src="js/manutencaoVistorias.js"></script>
            
            <c:forEach var="item" items="${vetorMaquinas}">
                <script>
                    try {
                        criarSlotMaquina("${item.nome}", "${item.codigo}", "${item.getUrlImagem()}");
                    } catch (erro) {
                        console.log("Erro ao criar as máquinas: " + erro);
                    }
                </script>  
            </c:forEach>
            <div id="calendario">
                <%@include file="../../comuns/Calendario.jsp" %>
            </div>   

            <div id="notificacoes" class="slot">
                
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
        </article>    
            
        <article id="feedbackRetornado">
        </article>
            
        
    </body>
</html>
