<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.dto.LocatarioDTO" %>
<%@page import="br.cefetmg.snacksmart.dto.FornecedorDTO" %>
<%@page import="java.util.ArrayList" %>
<%@include file="../../comuns/JSTL.jsp" %>
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/fornecedores.css">
        <title>Fornecedores</title>
        <%@include file="../../comuns/jqueryLink.jsp" %>
    </head>
    <body>        
        <%@include file="../../comuns/retornarInicial.jsp" %>
        <main id="fornecedorMain">     
            <h1 id="tituloDaPagina">Fornecedores</h1>   
            <div id="bloquearConteudo"></div>
            <div id="pesquisarFornecedor" class="slot">
                <input type="text" placeholder="Procurar por fornecedor" id="busca"></input>
                <button id="AddFonercedor">Adicionar Fornecedor</button>
                <div id="resultFornecedor" ></div>
                <!-- Cria as variáveis das consultas SQL -->
                <script>
                    var vetorFornecedores = [
                        <c:forEach items="${vetorFornecedores}" var="fornecedor">
                            {
                                nome: '${fornecedor.nome}',
                                telefone: '${fornecedor.telefone}',
                                email: '${fornecedor.email}',
                                locatario: '${fornecedor.locatario.nome}',
                                id:'${fornecedor.id}'
                            },
                        </c:forEach>
                    ];        
                    <c:set var="usuario" value="${usuarioResponsavel}" />
                    var usuario = {
                        nome: "${usuario.nome}", 
                        cpf: "${usuario.CPF}"
                    };
                </script>             
            </div>
            
            <div id="boxInfo" class="slot">
                <form action="GerenciarFornecedores" method="post">
                    <input id="nomeExibido" type="text" name="nomeExibido">
                    <input id="telefoneExibido" type="tel" name="telefoneExibido">
                    <input id="emailExibido" type="email" name="emailExibido">   
                    <input id="idFornecedor" type="hidden" name="idFornecedor">  
                    <input type="submit" id="atualizarDados" value="Realizar Alterações">                  
                </form>
                <button id="reverter">Reverter Alterações</button>
            </div>
            
        </main>
        
        <article id="addContato">
            <form action="GerenciarFornecedores" method="post">
                <input type="hidden" name="addForm" value="addForm">
                <label id="nomeInput">Nome completo do fornecedor:</label>
                <input id="nome" type="text" name="nome">
                
                <label id="telefoneInput">Telefone de contato:</label>
                <input type="tel" id="telefone" name="telefone">
                
                <label id="emailInput">E-mail do Fornecedor:</label>
                <input id="email" type="email" name="email">
                
                <input id="locatario" type="hidden" name="locatario"> 
                
                <input id="enviar" type="submit">    
            </form>
            <button class="cancelar">Cancelar</button>
        </article>
            
        <script src="js/fornecedores.js"></script>
        <c:forEach var="item" items="${vetorFornecedores}">
            <script>
                try {
                    criarSlot("${item.nome}", "${item.telefone}", "${item.email}");
                } catch (erro) {
                    console.log("Erro ao criar os fornecedores " + erro);
                }
            </script>  
        </c:forEach>
    </body>   
</html>
