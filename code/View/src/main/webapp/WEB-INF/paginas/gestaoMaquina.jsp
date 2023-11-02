<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.dto.MaquinaDTO" %>
<%@page import="java.util.ArrayList" %>
<%@include file="../../comuns/JSTL.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/gestaomaquina.css">
        <title>Gestão de Máquinas</title>
        <%@include file="../../comuns/jqueryLink.jsp" %>

    </head>
    <body>
        <%@include file="../../comuns/retornarInicial.jsp" %>
        <h1 id="tituloDaPagina">Máquinas</h1>
        <div id="bloquearConteudo"></div>
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
            
            <article id="formAddMaquina">               
                <form action="GerenciarMaquina" method="post" enctype="multipart/form-data">                                 
                    <input name="formSelecionado" type="hidden" value="formAddMaquina">
                    
                    <h1 id="tituloForm">Adicionar nova máquina</h1>
                    <h2 id="subtituloForm">Preencha todos os campos abaixos</h2>
                    
                    <label id="nomeDaMaquina">Nome da Máquina:</label>
                    <input class="preencher" type="text" id="nome" name="nome" minlength="5" maxlength="32" required><br>

                    <label id="tipoInput">Tipo da máquina:</label>                       
                    <select id="tipo" name="tipo" required>
                        <option value="REFRIGERADA">Refrigerada</option>
                        <option value="NAO_REFRIGERADA">Não refrigerada</option>
                    </select><br>
                    
                    <label id="labelImagem" class="imagem">Foto da máquina:</label>
                    <input type="file" name="imagem" id="inputImagem" accept="image/png" class="imagem"  required ><br>
                    
                    <label id="locatarioInput">Locatário responsável:</label>
                    <select id="locatario" name="locatario" required>
                    </select><br>  
                
                    <label id="localizacaoText">Localização (CEP):</label>
                    <input class="preencher" type="text" id="localizacao" name="localizacao" required><br>
                    
                    <input id="enviarformAddMaquina"class="botaoForm" type="submit" value="Enviar">
                    <div id="cancelarformAddMaquina" class="botaoForm cancelar">Cancelar</div>
                </form>
            </article>
                          
            <article id="formAtualizarMaquina">               
                <form action="GerenciarMaquina" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="formSelecionado" value="formAtualizarMaquina">
                    <input type="hidden" name="atualizarMaquinaCodigo" id="atualizarMaquinaCodigo">
                    <h1 id="tituloUpdate">Atualizar dados da máquina</h1>
                    
                    <label id="novoNomeLabel">Alterar nome da Máquina:</label>
                    <input type="text" id="novoNome" name="novoNome" minlength="5" maxlength="32"><br>
                    
                    <label id="novaLocalizacaoLabel">Nova localização (CEP):</label>
                    <input type="text" id="novaLocalizacao" name="novaLocalizacao"><br>
                    
                    <label id="novoLocatarioInput">Alterar locatário:</label>
                    <select id="novoLocatario" name="novoLocatario">
                    </select><br>  
                    
                    <label id="statusLabel">Alterar status da máquina:</label>
                    <select id="status" name="status">
                        <option value="DISPONIVEL">Disponível</option>
                        <option value="ALUGADA">Alugada</option>
                        <option value="EM_MANUTANCAO">Em manutenção</option>
                        <option value="AGUARDANDO_MANUTENCAO">Aguardando manutenção</option>                       
                    </select><br>    
                    
                    <label id="labelNovaImagen" class="imagem">Alterar foto da máquina:</label>
                    <input id="inputNovaImagen" type="file" accept="image/png" class="imagem" name="novaImagem"><br>
                                       
                    <input id="enviarformAtualizarMaquina"class="botaoForm" type="submit" value="Realizar Alterações">
                    <div id="cancelarformAtualizarMaquina" class="botaoForm cancelar">Cancelar</div>
                </form>
            </article> 
            
            <article id="remocaoMaquina">
                <h1>Você tem certeza?</h1>
                <h2>Ao remover a máquina, todos os dados relacionados a ela serão excluídos!</h2>
                <p>⚠ Dados relacionados à máquina e ao locatário não poderão serem acessados posteriormente.
                    Caso a máquina esteja ligada a algum cliente é importante que o locatário esteja ciente disso.</p>
                <form action="GerenciarMaquina" method="post">
                    <input type="hidden" name="formSelecionado" value="remocaoMaquina">
                    <input type="hidden" name="removerMaquinaCodigo" id="removerMaquinaCodigo" value="">
                    
                    <div class="botoesForm">
                        <input class="botaoForm" name="remover" type="submit" value="REMOVER MÁQUINA">
                        <div class="botaoForm cancelar">Cancelar</div>
                    </div>
                </form>
            </article>
            
            <article id="informacaoMaquina">       
                
                <h1 id="nomeMaquina">Máquina 01</h1>
                <h1 id="codeMaquina">COD-001</h1>
                <h2 id="locatarioMaquina">👤Locatário responsável: Geraldo Azeved</h2> 
                <h2 id="LocalizacaoDaMaquina">📍Localização: Bahia, Salvador</h2>              
                <h2 id="statusDinamicoH2">Status da Máquina: Disponível</h2> 
                <h2 id="tipoMaquina">Tipo da Máquina: Refrigerada</h2>   
                
                <div class="botoesForm">
                    <input class="botaoForm" id="atualizarDados" type="submit" value="Atualizar dados">
                    <div class="botaoForm cancelar">Voltar</div>
                </div>
                
            </article>
        </main>             
        <script src="js/maquinaInfo.js"></script> 
        <c:forEach var="item" items="${vetorMaquinas}">
                <script>
                    try {
                        criarSlotMaquina("${item.nome}", "${item.codigo}", "${item.status}", "${item.imagem}");
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
                        locatario: '${maquina.locatario}',
                        localizacao: '${maquina.localizacao}',
                        status: '${maquina.status}',
                        tipo: '${maquina.tipo}'
                    },
                </c:forEach>
            ];
        </script>
    </body>
</html>
