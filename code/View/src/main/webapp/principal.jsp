<%-- 
    Document   : principal
    Created on : 29 de out. de 2023, 00:29:11
    Author     : eloym
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.utils.enums.TipoUsuario" %>
<%@page import="br.cefetmg.snacksmart.dto.IUsuarioDTO" %>
<%@include file="comuns/taglibs.jsp" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/telaPrincipal.css">
        <title>Tela principal</title>
    </head>
    <body>
        <main>
            <h1>Olá, ${usuario.getNome()}</h1>
            
            
            <div id="menu">
                <section>
                    <div><a href="visualizarContratos"><button><h2>Visualizar Contrato</h2></button></a></div>
                    <div><a href="gestaoMaquina"><button><h2>Gestão Máquina</h2></button></a></div>
                    <c:choose>
                        <c:when test="${tipoUsuario == LOCATARIO}">
                            <div><a href="gestaoFornecedores"><button><h2>Fornecedor</h2></button></a></div>
                            <div><a href="GestaoLote"><button><h2>Informações de Estoque</h2></button></a></div>
                            <div><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></div>
                            <div><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></div>
                        </c:when>
                        <c:otherwise>
                            <div><a href="FinanceiroGerente.jsp"><button><h2>Financeiro</h2></button></a></div>
                            <div><a href="gestaoLocatarios"><button><h2>Gestão de Locatários</h2></button></a></div>
                            <div><a href="manutecaoVistorias"><button><h2>Manuteção Vistorias</h2></button></a></div>
                            <div><a href="relatorioGerente"><button><h2>Emissão Relatorios</h2></button></a></div>
                        </c:otherwise>
                    </c:choose>
                    <div><a href="meusDados.jsp"><button><h2>Meus dados</h2></button></a></div>
                    <div><a href="LoginServlet"><button><h2>Encerrar Sessão</h2></button></a></div>
                </section>
            </div>
        </main>
    </body>
</html>
