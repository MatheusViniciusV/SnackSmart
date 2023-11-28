<%-- 
    Document   : principal
    Created on : 29 de out. de 2023, 00:29:11
    Author     : eloym
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.utils.enums.TipoUsuario" %>
<%@page import="br.cefetmg.snacksmart.dto.IUsuarioDTO" %>
<%@include file="comuns/taglibs.jsp" %>

<% 
    TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
    IUsuarioDTO usuario = (IUsuarioDTO) session.getAttribute("usuario");
%>


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
            <h1>Olá, <%=usuario.getNome()%></h1>
            
            
            <div id="menu">
                <ul>
                    <li><a href="visualizarContratos"><button><h2>Visualizar Contrato</h2></button></a></li>
                    <li><a href="gestaoMaquina"><button><h2>Gestão Máquina</h2></button></a></li>
                    <c:choose>
                        <c:when test="${tipoUsuario == TipoUsuario.LOCATARIO}">
<<<<<<< HEAD
                            <li><a href="gestaoFornecedores"><button><h2>Fornecedor</h2></button></a></li>                         
                            <li><a href="GestaoLote"><button><h2>Informações de Estoque</h2></button></a></li>
=======
                            <li><a href="visualizarContratos"><button><h2>blablabla</h2></button></a></li>
                            <li><a href="gestaoMaquina"><button><h2>Gestão Máquina</h2></button></a></li>
                            <li><a href="gestaoFinanceiroLocatario"><button><h2>Financeiro</h2></button></a></li>
>>>>>>> 017ebea99d28d781aa0505a57e9a5b770df1fd8a
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                            <li><a href="MeusDadosLocatario.jsp"><button><h2>Meus dados</h2></button></a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="FinanceiroGerente.jsp"><button><h2>Financeiro</h2></button></a></li>
                            <li><a href="GestaoLocatarios.jsp"><button><h2>Gestão de Locatários</h2></button></a></li>
                            <li><a href="manutecaoVistorias"><button><h2>Manuteção Vistorias</h2></button></a></li>
                            <li><a href="EmissaoRelatorio"><button><h2>Emissão Relatorios</h2></button></a></li>
                            <li><a href="MeusDadosGerente.jsp"><button><h2>Meus dados</h2></button></a></li>
                        </c:otherwise>
                    </c:choose>                    
                    <li><a href="LoginServlet"><button><h2>Encerrar Sessão</h2></button></a></li>
                </ul>
                <div id="meus-dados-btn">
                </div>
            </div>
        </main>
    </body>
</html>
