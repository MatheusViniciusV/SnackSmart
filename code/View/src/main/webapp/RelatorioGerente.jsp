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
</head>
<body>
    <h1>Relatório Geral</h1>
    <table border="1">
        <tr>
            <td>Maquinas</td>
        </tr>
        <tr>
            <th>Codigo</th>
            <th>Localização</th>
            <th>Valor</th>
            <th>Estado</th>
        </tr>
        <%
            ArrayList<MaquinaDTO> ArrayList = new ArrayList<MaquinaDTO>();
            MaquinaDAO maquinaDAO = new MaquinaDAO();
            maquinas = maquinaDAO.listarTodos();
            
            for(MaquinaDTO m:maquinas){
        %>
        <tr>
            <td><%=m.getCodigo()%></td>
            <td><%=m.getLocalizacao()%></td>
            <td><%=m.getValor()%></td>
            <td><%=m.getEstado()%></td>
        </tr>
        <%}%>
    </table>
</body>
</html>

