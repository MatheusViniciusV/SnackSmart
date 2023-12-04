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
            <div id="pesquisarLote" class="slot">
                <input type="text" placeholder="🔎 Procurar por lote de produto" id="busca">
                <button id="addLote">Adicionar novo lote</button>
                <div id="resultLote" ></div>                
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
                                id: '${lote.id}',
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
                    <p id="compraProduto"></p>
                    <p id="vendaProduto"></p>
                </div>
                
                <button id="atualizarLote">Alterar Informações</button>
                <form action="GerenciarEstoque" method="post">
                    <input type="hidden" name="remover" value="remover">
                    <input type="hidden" class="loteId" name="loteId">                                 
                    <input type="submit" value="Remover Lote" id="removerLote">
                </form>
            </div>
            
        </main>
        
        <article id="formLote" slot="slot">  
            <form action="GerenciarEstoque" method="post" enctype="multipart/form-data">
                <h1 class="formh1">Adicionar novo Lote</h1>
                
                <label id="tipoProdutoLabel">Nome do Produto:</label>
                <input placeholder="Nome do produto" id="tipoProduto" type="text" name="tipoProduto" required>
                
                <label id="quantidadeLabel">Quantidade:</label>
                <input placeholder="Quantidade" id="quantidade"  type="number" name="quantidade" required>
                
                <label id="precoCompraLabel">Preço de Compra:</label>
                <input placeholder="($00.00)Valor de 1ª Und." id="precoCompra" name="precoCompra" class="dinheiro" required>
                
                <label id="precoVendaLabel">Preço de Venda:</label>
                <input placeholder="($00.00)Valor de 1ª Und." id="precoVenda" name="precoVenda" class="dinheiro" required>
                
                <label id="fornecedorLabel">Fornecedor do produto:</label>
                <select id="fornecedor" name="fornecedor">
                </select><br>    
                
                <input type="file" name="imagem" accept="image/*" id="imagem"><br>
                <input type="hidden" value="novoLote" name="novoLote">
                <input id="enviarLote" type="submit" value="Adicionar novo lote">
            </form>
            <button class="cancelar">Cancelar</button>
        </article>
                    
                    
        <article id="formAtualizarLote" slot="slot">  
            <form action="GerenciarEstoque" method="post" enctype="multipart/form-data">
                <input type="hidden" name="atualizar" value="atualizar">
                <input type="hidden" class="loteId" name="loteId">
                <h1 class="formh1">Informações do Lote</h1>
                
                <label id="Novo-tipoProdutoLabel">Nome do Produto:</label>
                <input id="Novo-tipoProduto" type="text" name="novo-tipoProduto" required>
                
                <label id="Novo-quantidadeLabel">Quantidade:</label>
                <input id="Novo-quantidade"  type="number" name="novo-quantidade" required>
                
                <label id="Novo-precoCompraLabel">Preço de Compra:</label>
                <input id="Novo-precoCompra" name="novo-precoCompra" class="dinheiro" required>
                
                <label id="Novo-precoVendaLabel">Preço de Venda:</label>
                <input id="Novo-precoVenda" name="novo-precoVenda" class="dinheiro" required>
                
                <label id="Novo-fornecedorLabel">Fornecedor do produto:</label>
                <select id="Novo-fornecedor" name="novo-fornecedor">
                </select><br>    
                
                <input type="file" name="novo-imagem" accept="image/*" id="Novo-imagem"><br>
                
                <input id="enviarLote" type="submit" value="Atualizar informações">
                
            </form>
            <button class="cancelar">Cancelar</button>
        </article>            
            
        <script src="js/estoque.js"></script>
        <!-- Cria os slots dos lotes -->
        <c:forEach var="item" items="${vetorLotes}">
            <script>
                try {
                    criarSlotLotes("${item.tipoProduto}", "${item.quantidade}", "${item.getUrlImagem()}", "${item.id}");
                } catch (erro) {
                    console.log("Erro ao criar os produtos: " + erro);
                }
            </script>  
        </c:forEach>
        <!-- Carrega os fornecedores -->
        <script>            
            <c:forEach items="${vetorFornecedores}" var="fornecedor">
                var nome = "${fornecedor.nome}";
                vetorNomes.push(nome);
                var id = "${fornecedor.id}";
                vetorId.push(id);
            </c:forEach>  
        </script>   
    </body>   
</html>
