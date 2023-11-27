<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.dto.MaquinaDTO" %>
<%@page import="br.cefetmg.snacksmart.utils.enums.TipoUsuario"%>
<%@page import="java.util.ArrayList" %>
<%@include file="../../comuns/taglibs.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/gestaoMaquina.css">
        <title>Gestão de Máquinas</title>
        <%@include file="../../comuns/jqueryLink.jsp" %>

    </head>
    <body>
        <%@include file="../../comuns/retornarInicial.jsp" %>
        <h1 id="tituloDaPagina">Máquinas</h1>
        <div id="bloquearConteudo"></div>
        <main id="gestaoMaquinasMain">  
            <% 
                TipoUsuario tipoUsuario = (TipoUsuario) request.getAttribute("usuarioAcessando"); 
            %>            
            <c:if test="${tipoUsuario == LOCADOR}">
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
                        <input type="file" name="imagem" id="inputImagem" accept="image/*" class="imagem"><br>

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
                            <option value="EM_MANUTENCAO">Em manutenção</option>
                            <option value="AGUARDANDO_MANUTENCAO">Aguardando manutenção</option>                       
                        </select><br>    

                        <label id="labelNovaImagen" class="imagem">Alterar foto da máquina:</label>
                        <input id="inputNovaImagen" type="file" accept="image/*" class="imagem" name="novaImagem"><br>

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
            </c:if>  
            <c:if test="${tipoUsuario == LOCATARIO}">
               <article id="feedbackMaquina">
                    <form action="GerenciarMaquina" method="post">
                        <input type="hidden" name="formSelecionado" value="feedbackMaquina">
                        <input type="hidden" name="feedbackMaquinaCodigo" id="feedbackMaquinaCodigo" value="">
                        <h1 id="h1Feedback">Enviar Feedback</h1>
                        <textarea name="tituloFeedback" id="tituloFeedback" placeholder="Assunto"></textarea>
                        <textarea name="mensagemFeedback" id="mensagemFeedback"  placeholder="Escreva a mensagem"></textarea>
                        <input type="checkbox" name="solicitarManutencao" id="solicitarManutencao"  value="ERRO" />
                        <label id="labelCheck">Solicitar manutenção da máquina</label>
                        <input id="enviarFeedback" class="botaoForm" name="remover" type="submit" value="Enviar feedback">
                        <div id="cancelarFeedback" class="botaoForm cancelar">Cancelar</div>
                    </form>
                </article>
            </c:if>
            
            <article id="informacaoMaquina">       
                
                <h1 id="nomeMaquina"></h1>
                <h1 id="codeMaquina"></h1>
                <h2 id="locatarioMaquina"></h2> 
                <h2 id="LocalizacaoDaMaquina"></h2>              
                <h2 id="statusDinamicoH2"></h2> 
                <h2 id="tipoMaquina"></h2>   
                
                <div class="botoesForm">
                    <c:if test="${tipoUsuario == LOCADOR}">
                        <input class="botaoForm" id="atualizarDados" type="submit" value="Atualizar dados">
                    </c:if>
                    <div class="botaoForm cancelar">Voltar</div>
                </div>
                
            </article>
        </main>
        <script>
            var usuarioAcessando = '<c:out value="${tipoUsuario}"/>' ;
        </script>    
        <script src="js/maquinaInfo.js"></script> 
        <!-- Carrega os locatarios -->
        <c:if test="${tipoUsuario == LOCADOR}">
            <script>            
                <c:forEach items="${listaLocatarios}" var="locatario">
                    var nome = "${locatario.nome}";
                    vetorNomes.push(nome);
                </c:forEach>  
                <c:forEach items="${listaLocatarios}" var="locatario">
                    var cpf = "${locatario.CPF}";
                    vetorCPF.push(cpf);
                </c:forEach>
            </script>
        </c:if> 
        
        <!-- Carrega os slots das maquinas -->
        <c:forEach var="item" items="${vetorMaquinas}">
                <script>
                    try {
                        criarSlotMaquina("${item.nome}", "${item.codigo}", "${item.status}", "${item.getUrlImagem()}");
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
                        locatario: '${maquina.locatario.nome}',
                        localizacao: '${maquina.localizacao}',
                        status: '${maquina.status}',
                        tipo: '${maquina.tipo}'
                    },
                </c:forEach>
            ];
        </script>
        <script>
            if (vetorMaquinaArray[0] === undefined){
                let erro = document.createElement("h1");
                erro.textContent = "Não há máquinas cadastradas";
                let main = document.getElementById("gestaoMaquinasMain");
                main.appendChild(erro);
            }
        </script>
    </body>
</html>
