<%-- 
    Document   : RelartorioGeralGerente
    Created on : 24 de out. de 2023, 09:57:35
    Author     : marco
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import= "br.cefetmg.snacksmart.dao.MaquinaDAO" %>
<%@ page import= "br.cefetmg.snacksmart.dto.MaquinaDTO" %>
<%@ page import= "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <title>Relatório Geral</title>
    <link rel="stylesheet" href="css/financeiro.css">
</head>
<body>
    <%@include file="comuns/retornarInicial.jsp" %>
    <h1>Relatório Geral</h1>
    <table border="1">
        <tr  class="cabecalho"><th>Maquinas</th></tr>
        <tr>
            <th>Codigo</th>
            <th>Localização</th>
            <th>Valor</th>
            <th>Estado</th>
        </tr>

            <%ArrayList<MaquinaDTO> listMaquinas = (ArrayList<MaquinaDTO>) request.getAttribute("listMaquinas");        %>
            <c:forEach var="m" items="${listaMaquinas}">
        <tr>
            <td><c:out value="${m.getCodigo}"/></td>
            <td>'<c:out value="${m.getLocalizacao}"/>'</td>
            <td><c:out value="${m.getValor}"/></td>
            <td><c:out value="${m.getEStado}"/></td>
        </tr>
        <c:if test="${!loop.last}">, </c:if>
        </c:forEach>
    </table>
</body>
</html>

