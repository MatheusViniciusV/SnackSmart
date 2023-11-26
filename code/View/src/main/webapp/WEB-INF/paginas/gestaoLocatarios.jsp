<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../comuns/taglibs.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestão de Locatários</title>
        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/gestaoLocatarios.css">
    </head>
    <body>
        <%@include file="../../comuns/retornarInicial.jsp" %>
        <h1>Gestão de Locatários</h1>
        <main>
            <section>
                <form action="manterLocatarios" method="post">
                    <table>
                        <tr>
                            <td>
                                Locatário:
                            </td>
                            <td>
                                <select id="listaLocatarios" name='lista'>
                                    <option value="">Novo</option>
                                    <% int i = 0; %>
                                    <c:forEach var="locatario" items="${locatarios}">
                                        <option value="<%= i++ %>">${locatario.getNome()}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Nome:
                            </td>
                            <td>
                                <input id='nomeInput' type='text' name='nome' value='' >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                CPF:
                            </td>
                            <td>
                                <input id='CPFInput' type='text' name='cpf' value='' >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Senha:
                            </td>
                            <td>
                                <input id='senhaInput' type='text' name='senha' value=''>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Email:
                            </td>
                            <td>
                                <input id='emailInput' type='text' name='email' value='' >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Telefone:
                            </td>
                            <td>
                                <input id='telefoneInput' type='text' name='telefone' value='' >
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div id="botoes">
                                    <button type="submit" name="submitType" value="adicionar">Adicionar locatário</button>
                                    <button type="submit" name="submitType" value="atualizar">Atualizar locatário</button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </section>
        </main>
        <script>
            const arrayDeLocatarios = [
                <c:forEach var="locatario" items="${locatarios}">
                {
                    nome: '${locatario.getNome()}',
                    cpf: '${locatario.getCPF()}',
                    email: '${locatario.getEmail()}',
                    telefone: '${locatario.getTelefone()}',
                },
                </c:forEach>
            ];
            
            const listaLocatarios = document.querySelector("#listaLocatarios");

            listaLocatarios.addEventListener('change', function() {
                document.getElementById("nomeInput").value = arrayDeLocatarios[this.value].nome;
                document.getElementById("CPFInput").value = arrayDeLocatarios[this.value].cpf;
                document.getElementById("senhaInput").value = '';
                document.getElementById("emailInput").value = arrayDeLocatarios[this.value].email;
                document.getElementById("telefoneInput").value = arrayDeLocatarios[this.value].telefone;
            });
        </script>        
    </body>
</html>
