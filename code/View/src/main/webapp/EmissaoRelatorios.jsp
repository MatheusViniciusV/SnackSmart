<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.dto.FeedbackDTO" %>
<%@page import="br.cefetmg.snacksmart.dto.MaquinaDTO" %>
<%@page import="java.util.ArrayList" %>
<%@include file="../../comuns/JSTL.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="EmissaoRelatorios.css">
    <link rel="stylesheet" href="css/gestaomaquina.css">
    <title>Emissão de relatórios</title>
     <%@include file="../../comuns/jqueryLink.jsp" %>
</head>
<body>
    <%@include file="../../comuns/retornarInicial.jsp" %>
    <h1>Emissão de relatórios</h1>
        <main id="gestaoMaquinasMain"> 
             <% ArrayList<MaquinaDTO> listaMaquinas = (ArrayList<MaquinaDTO>) request.getAttribute("vetorMaquinas"); %>
            <script>
                var listaMaquinasJS = [
                    <c:forEach var="maquina" items="${listaMaquinas}">
                        {
                            nome: '<c:out value="${maquina.nome}" />',
                            codigo: <c:out value="${maquina.codigo}" />,
                            localizacao: '<c:out value="${maquina.localizacao}" />',
                            locatario: '<c:out value="${maquina.locatario}" />',
                            status: '<c:out value="${maquina.status}" />',
                            tipo: '<c:out value="${maquina.tipo}" />',
                            imagem: [
                                <c:forEach var="onebyte" items="${maquina.imagem}">
                                    '<c:out value="${onebyte}" />'<c:if test="${!loop.last}">,</c:if>
                                </c:forEach>
                            ]
                        }<c:if test="${!loop.last}">, </c:if>
                    </c:forEach>
                ];

            </script>
            <article id="informacaoMaquina">       
                
                <h1 id="nomeMaquina">Máquina 01</h1>
                <h1 id="codeMaquina">COD-001</h1>
                <h2 id="tipoMaquina">Tipo da Máquina: Refrigerada</h2>   
                <div class="botoesForm">
                    <div class="botaoForm cancelar">Voltar</div>
                </div>
                
            </article>
        </main>
    <script src="js/maquinaInfo.js"></script>
    <c:forEach var="item" items="${vetorMaquinas}">
                <script>
                    try {
                        criarSlotMaquina("${item.nome}", "${item.codigo}","${item.imagem}");
                    } catch (erro) {
                        console.log("Erro ao criar as máquinas: " + erro);
                    }
                </script>
                
        </c:forEach>
        <!-- Mostrar Info do slot selecionado-->
        <script>
            var vetorMaquinaArray = [
                <c:forEach items="${vetorMaquinas}" var="maquina">
                    {
                        nome: '${maquina.nome}',
                        codigo: '${maquina.codigo}',
                        tipo: '${maquina.tipo}'
                    },
                </c:forEach>
            ];
        </script>
    <section>
        <script>
                    try {
                        criarSlotMaquina("${item.nome}", "${item.codigo}", "${item.status}", "${item.imagem}");
                    } catch (erro) {
                        console.log("Erro ao criar as máquinas: " + erro);
                    }
        </script>
        <button>Emitir relatório de Feedbacks</button>
        <div id="emitirRelatorio"><a href="RelatorioGerente.jsp">Emitir Relatorio Geral</a></div>
    </section>
</body>
</html>