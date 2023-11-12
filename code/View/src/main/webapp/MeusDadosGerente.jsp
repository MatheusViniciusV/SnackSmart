<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.dto.GerenteDTO" %>
<%@include file="comuns/taglibs.jsp" %>

<% 
    GerenteDTO usuario = (GerenteDTO) session.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar meus dados</title>
    </head>
    <body>
        <%@include file="comuns/retornarInicial.jsp" %>
        <h1>Alterar meus dados</h1>
        <form action="AlterarDadosGerente" name="alterarMeusDados" method="post">
            <table>
                <tr>
                    <td>
                        Nome:
                    </td>
                    <td>
                        <input type='text' name='nome' value='<%=usuario.getNome()%>' >
                    </td>
                </tr>
                <tr>
                    <td>
                        CPF:
                    </td>
                    <td>
                        <input type='text' name='cpf' value='<%=usuario.getCPF()%>' >
                    </td>
                </tr>
                <tr>
                    <td>
                        Email:
                    </td>
                    <td>
                        <input type='text' name='email' value='<%=usuario.getEmail()%>' >
                    </td>
                </tr>
                <tr>
                    <td>
                        Telefone:
                    </td>
                    <td>
                        <input type='text' name='telefone' value='<%=usuario.getTelefone()%>' >
                    </td>
                </tr>
                <tr>
                    <td>
                        RG:
                    </td>
                    <td>
                        <input type='text' name='rg' value='<%=usuario.getRG()%>' >
                    </td>
                </tr>
                <tr>
                    <td>
                        Senha:
                    </td>
                    <td>
                        <input type='password' name='senha'> 
                    </td>
                </tr>
            </table> 
            <input type="submit" value="Alterar dados">
        </form>
    </body>
</html>
