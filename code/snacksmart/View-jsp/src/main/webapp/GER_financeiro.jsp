<%-- 
    Document   : GER_financeiro
    Created on : 3 de out. de 2023, 11:00:04
    Author     : Aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String Recebimeto = (String) request.getAttribute("Recebimento");
           String Gastos = (String) request.getAttribute("Gastos");
           String Saldo = (String) request.getAttribute("Saldo")
        %>
        <h1>Finaceiro</h1>
        <div class="box">
            <p>Recebimento previsto para o mês:</p>
            <% out.print(Recebimento); %>
        </div>
        <div class="box">
            <p>Gastos feitos neste mês:</p>
            <% out.print(Gastos); %>
        </div>
        <div class="box">
            <p>Saldo total</p>
            <% out.print(Saldo); %>

        </div>
        
    </body>
</html>
