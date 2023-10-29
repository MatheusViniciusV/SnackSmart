<%-- 
    Document   : principal
    Created on : 29 de out. de 2023, 00:29:11
    Author     : eloym
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.utils.enums.TipoUsuario" %>
<%@include file="comuns/taglibs.jsp" %>

<% 
    TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario"); 
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/principal.css">
        <title>Tela principal</title>
    </head>
    <body>
        <main>
            <h1>Olá, <%=tipoUsuario%></h1>
            
            
            <div id="menu">
                <ul>
                    <li><a href="visualizarContratos.jsp"><button><h2>Visualizar Contrato</h2></button></a></li>
                    <c:choose>
                        <c:when test="${tipoUsuario == TipoUsuario.LOCATARIO}">
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                            <li><a href="visualizarContratos.jsp"><button><h2>blablabla</h2></button></a></li>
                        </c:otherwise>
                    </c:choose>                    
                    <li><a href="visualizarContratos.jsp"><button><h2>Meus dados</h2></button></a></li>
                    <li><a href="LoginServlet"><button><h2>Encerrar Sessão</h2></button></a></li>
                </ul>
                <div id="meus-dados-btn">
                </div>
            </div>
        </main>
    </body>
</html>
