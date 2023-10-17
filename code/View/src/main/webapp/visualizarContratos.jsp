<%-- 
    Document   : visualizarContratos
    Created on : 8 de out. de 2023, 12:00:56
    Author     : eloym
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- 
    Será o mesmo jsp para locador e locatario, quando eu tiver com 
    as dependencias corretas vou fazer a view separar a visão de cada 
    da tela.
--%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/contratos.css">
        <title>Contratos</title>
    </head>
    <body>
        <%@include file="comuns/retornarInicial.jsp" %>
        <main>
            <h1>Contratos</h1>
            <section id="lista-contratos">
                <article class="contratos" id="contrato-01" data-id="01">
                    <h3>Contrato 1</h3>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </article>
                <article class="contratos">
                    <h3>Contrato 2</h3>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </article>
            </section>

            <section id="criar-contrato-form" class="oculto">
                <form action="CriarContrato" method="post">
                    <h2>Novo contrato</h2>
                    <h3>Dados do Locador</h3>
                    <label>Nome: <br>
                        <input type="text" name="locador-nome" readonly="readonly" value="Algum nome">
                    </label>
                    <label class="cpf">CPF <br>
                        <input type="text" name="locador-cpf" readonly="readonly" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" value="000.000.000-01">
                    </label>
                    <label class="telefone">Telefone: <br>
                        <input type="tel" name="locatador-telefone">
                    </label>
                    <label>Email: <br>
                        <input type="email" name="locador-email">
                    </label>
                    <h3>Dados do Locatário</h3>
                    <label>Nome:  <br>
                        <input type="text" name="locatario-nome" id="">
                    </label>
                    <label class="cpf">CPF: <br>
                        <input type="text" name="locatario-cpf" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}">
                    </label>
                    <label class="telefone">Telefone: <br>
                        <input type="tel" name="locatario-telefone">
                    </label>
                    <label>Email: <br>
                        <input type="email" name="locatario-email">
                    </label>
                    <h3>Dados do contrato</h3>
                    <div id="datas">
                        <label>Data de inicio: <br>
                            <input type="date" name="data-inicio" id="">
                        </label>
                        <label>Data de término: <br>
                            <input type="date" name="data-termino" id="">
                        </label>
                        <label>Data mensal de pagamento: <br>
                            <input type="date" name="data-pagamento" id="">
                        </label>
                    </div>
                    <label>Observações: <br>
                        <textarea name="observacoes" wrap="hard" cols="85"></textarea>
                    </label>
                    <div id="boteos-criar-contrato">
                        <button><h3>Criar Contrato</h3></button>
                        <button><h3>Cancelar</h3></button>
                    </div>
                </form>
            </section>
            <div id="botoes">
                <button id="solicita-cancelar-contrato" class="null"><h2>Solicitar cancelamento</h2></button>
                <button id="criar-contrato"><h2>Criar Novo Contrato</h2></button>
                <button id="pdf-contrato" class="null"><a><h2>Emitir PDF</h2></a></button>
            </div>
        </main>



        <%@include file="comuns/jqueryLink.jsp" %>
        <script src="js/contratos.js"></script>
    </body>
</html>
