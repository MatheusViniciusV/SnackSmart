<%-- 
    Document   : visualizarContratos
    Created on : 8 de out. de 2023, 12:00:56
    Author     : eloym
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../comuns/taglibs.jsp" %>

<%-- 
    Será o mesmo jsp para locador e locatario, quando eu tiver com 
    as dependencias corretas vou fazer a view separar a visão de cada 
    da tela.
--%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/contratos.css">
        <link rel="stylesheet" href="css/style.css">
        <title>Contratos</title>
    </head>
    <!-- TODO fazer opção de filtro e busca de contratos -->
    <body>
        <%@include file="../../comuns/retornarInicial.jsp" %>
        <main>
            <!-- TODO fazer essa lista aparecer de maneira dinamica -->
            <h1>Contratos </h1>
            <section id="lista-contratos">
                <c:forEach var="contrato" items="${contratos}">

                    <article class="contratos" id="contrato-${contrato.getId()}" data-id="${contrato.getId()}">
                        <h3>Contrato ${contrato.getId()}</h3>
                        <c:if test="${tipoUsuario == LOCADOR}">
                            <div>Locatario: ${contrato.getLocatario()}</div>
                        </c:if>
                        <div>Data inicio: ${contrato.getDataInicio()}</div>
                        <div>Data fim: ${contrato.getDataFim()}</div>
                        <div>Dia do pagamento: ${contrato.getDataPagamento().getDia()}</div>
                        <div>Valor: ${contrato.printValorPagamento()}</div>
                        <div>Estados:
                            <span class="${contrato.getStatus().toString().toLowerCase()}">
                                ${contrato.getStatus().toString().toLowerCase()}
                            </span>
                        </div>
                    </article>
                </c:forEach>
            </section>
            
            <form action="visualizarContratos" method="get">
                <c:if test="${tipoUsuario == LOCADOR}">
                    <label>
                        CPF do Locatário:
                        <input type="text" name="cpf">
                    </label>
                </c:if>
                <label>
                    Ordenar por:    
                    <select name="ordenacao">
                        <c:forEach var="tipo" items="${tipoOrdenacao}">
                            <option value="${tipo.value()}">${tipo.toString()}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>
                    Exibir estado:    
                    <select name="status">
                        <c:forEach var="tipo" items="${tipoStatus}">
                            <option value="${tipo.toString()}">${tipo.toString().toLowerCase()}</option>
                        </c:forEach>
                        <option value="todos">todos os estados</option>
                    </select>
                </label>
                <button>Buscar</button>
                <button><a href="visualizarContratos">Limpar busca</a></button>
            </form>
            
            <c:if test="${tipoUsuario == LOCADOR}">
                <section id="criar-contrato-form" class="oculto">
                    <form action="CriarContrato" method="post">
                        <h2>Novo contrato</h2>
                        <span class="obrigatorio">obrigatório *</span>
                        <h3>Dados do Locador</h3>
                        <div id="dados-locador">
                            <label>Nome: <br>
                                <input type="text" name="locador-nome" readonly="readonly" value="${usuario.getNome()}">
                            </label>
                            <label class="cpf">CPF <br>
                                <input type="text" name="locador-cpf" readonly="readonly" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" value="${usuario.getCPF()}">
                            </label>
                            <label>Email: <br>
                                <input type="email" name="locador-email" readonly="readonly" value="${usuario.getEmail()}" class="obrigatorio">
                            </label>
                            <label class="telefone">Telefone: <br>
                                <input type="tel" name="locador-telefone" readonly="readonly" value="${usuario.getTelefone()}" class="obrigatorio">
                            </label>
                        </div>
                        <h3>Dados do Locatário</h3>
                        <div id="dados-locatario">
                            <label>Nome: <br>
                                <input type="text" name="locatario-nome">
                            </label>
                            <label class="cpf">CPF: <abbr title="Obrigatório"><span class="obrigatorio">*</span></abbr> <br>
                                <input type="text" name="locatario-cpf" placeholder="000.000.000-00" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}">
                            </label>
                            <label>Email: <br>
                                <input type="email" placeholder="emailexemplo@exemplo.com" name="locatario-email" class="obrigatorio">
                            </label>
                            <label class="telefone">Telefone: <br>
                                <input type="tel" placeholder="(00)000000000" name="locatario-telefone" class="obrigatorio">
                            </label>
                        </div>
                        <h3>Dados do contrato</h3>
                        <div id="dados-contrato">
                            <label>
                                Valor mensal: <abbr title="Obrigatório"><span class="obrigatorio">*</span></abbr> <br>
                                <input type="number" name="valor" min="0" step="0.01">
                            </label>
                            <label>
                                Tipo de máquina: <br>
                                <select>
                                    <option>Sla</option>
                                    <option>Sla</option>
                                </select>
                            </label>
                            <label>
                                Código da máquina: <abbr title="Obrigatório"><span class="obrigatorio">*</span></abbr> <br>
                                <input type="text" name="codigo-maquina">
                            </label>
                            <label>Data de inicio: <abbr title="Obrigatório"><span class="obrigatorio">*</span></abbr> <br>
                                <input type="date" name="data-inicio">
                            </label>
                            <label>Data de término: <abbr title="Obrigatório"><span class="obrigatorio">*</span></abbr> <br>
                                <input type="date" name="data-termino">
                            </label>
                            <label>Dia de pagamento: <abbr title="Obrigatório"><span class="obrigatorio">*</span></abbr> <br>
                                <input type="number" name="dia-pagamento" min="1" max="28">
                            </label>
                            <label>Observações: <br>
                                <textarea name="observacoes" wrap="hard" cols="85" maxlength="510"
                                    placeholder="escreva informações adicionais que não estão contidas nos campos anteriores."
                                ></textarea>
                            </label>
                        </div>
                        <div id="botoes-criar-contrato">
                            <button type="button" id="enviar"><h3>Criar Contrato</h3></button>
                            <button type="button" id="cancelar"><h3>Cancelar</h3></button>
                        </div>
                    </form>
                </section>
            </c:if>
            
            <div id="botoes">
                <button id="pdf-contrato" class="null"><a><h2>Emitir PDF</h2></a></button>
                <c:choose>
                    <c:when test="${tipoUsuario == LOCADOR}">
                        <button id="cancelar-contrato" class="null"><h2>Cancelar Contrato</h2></button>
                        <button id="criar-contrato"><h2>Criar Novo Contrato</h2></button>
                    </c:when>
                    <c:otherwise>
                        <button id="solicita-cancelar-contrato" class="null" data-calcelar="SolicitarCancelarContrato"><h2>Solicitar cancelamento</h2></button>
                    </c:otherwise>
                </c:choose>        
            </div>
        </main>

        <%@include file="../../comuns/validarRegEx.jsp" %>
        <%@include file="../../comuns/jqueryLink.jsp" %>
        <script src="js/contratos.js"></script>
        <c:if test="${tipoUsuario == LOCADOR}">
            <script src="js/criarContrato.js"></script>
        </c:if>
    </body>
</html>
