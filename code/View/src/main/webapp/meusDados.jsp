<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.dto.GerenteDTO" %>
<%@include file="comuns/taglibs.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar meus dados</title>
        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/meusDados.css">
    </head>
    <body>
        <%@include file="comuns/retornarInicial.jsp" %>
        <h1>Alterar meus dados</h1>
        <main>
            <c:choose>
                <c:when test="${tipoUsuario == LOCADOR}">
                    <form action="AlterarDadosGerente" name="alterarMeusDados" method="post">
                </c:when>
                <c:otherwise>
                    <form action="AlterarDadosLocatario" name="alterarMeusDados" method="post">
                </c:otherwise>
            </c:choose>
                <table>
                    <tr>
                        <td>
                            Nome:
                        </td>
                        <td>
                            <input type='text' name='nome' value='${usuario.getNome()}' >
                        </td>
                    </tr>
                    <c:if test="${tipoUsuario == LOCADOR}">
                        <tr>
                            <td>
                                CPF:
                            </td>
                            <td>
                                <input type='text' name='cpf' value='${usuario.getCPF()}' >
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            Email:
                        </td>
                        <td>
                            <input type='text' name='email' value='${usuario.getEmail()}' >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Telefone:
                        </td>
                        <td>
                            <input type='text' name='telefone' value='${usuario.getTelefone()}' >
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
                <button>Alterar dados</button>
            </form>
        </main>
    </body>
</html>
