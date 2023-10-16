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
                <article class="contratos">
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
            <div id="botoes">
                <button id="solicita-cancelar-contrato" class="null"><h2>Solicitar cancelamento</h2></button>
                ­<button id="criar-contrato"><h2>Criar Novo Contrato</h2></button>
                ­<button id="pdf-contrato"><h2>Emitir PDF</h2></button>
            </div>
        </main>

        <%@include file="comuns/jqueryLink.jsp" %>
        <script src="js/contratos.js"></script>
    </body>
</html>
