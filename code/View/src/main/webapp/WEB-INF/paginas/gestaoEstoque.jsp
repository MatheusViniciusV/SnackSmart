<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.dto.LocatarioDTO" %>
<%@page import="br.cefetmg.snacksmart.dto.FornecedorDTO" %>
<%@page import="java.util.ArrayList" %>
<%@include file="../../comuns/taglibs.jsp" %>
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/estoque.css">
        <title>Informações Estoque</title>
        <%@include file="../../comuns/jqueryLink.jsp" %>
    </head>
    <body>        
        <%@include file="../../comuns/retornarInicial.jsp" %>
        <main id="fornecedorMain">     
            <h1 id="tituloDaPagina">Informações do Estoque</h1>   
            <div id="bloquearConteudo"></div>
            <div id="pesquisarMaquina" class="slot">
                <input type="text" placeholder="Procurar por máquina" id="busca"></input>
                <div id="resultMaquina" ></div>
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
                    var vetorLotes = [
                        <c:forEach items="${vetorLotes}" var="lote">
                            {
                                tipoProduto: '${lote.tipoProduto}',
                                quantidade: '${lote.quantidade}',
                                precoCompra: '${lote.precoCompra}',
                                precoVenda: '${lote.precoVenda}',
                                url:'${lote.getUrlImagem()}',
                                locatario: '${lote.locatario.nome}',
                                fornecedor: '${lote.fornecedor.nome}'
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
                <img id="imagemProduto" src="img/noneProduto.png" alt="">
                <div id="textosProduto">
                    <p id="nomeProduto">SELECIONE UM PRODUTO</p>
                    <p id="fornecedorProduto"></p>
                </div>
            </div>
            
        </main>
        
        <article id="addLote">
            <form action="GerenciarEstoque" method="post">
                <in
            </form>
            <button class="cancelar">Cancelar</button>
        </article>
            
        <script src="js/estoque.js"></script>
        <!-- Cria os slots dos lotes -->
        <c:forEach var="item" items="${vetorLotes}">
            <script>
                try {
                    criarSlotLotes("${item.nome}", "${item.codigo}", "${item.getUrlImagem()}");
                } catch (erro) {
                    console.log("Erro ao criar os produtos: " + erro);
                }
            </script>  
        </c:forEach>
    </body>   
</html>
