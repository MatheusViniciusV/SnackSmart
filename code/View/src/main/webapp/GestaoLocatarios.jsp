<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.snacksmart.dao.LocatarioDAO" %>
<%@page import="br.cefetmg.snacksmart.dto.LocatarioDTO" %>
<%@page import="java.util.ArrayList" %>
<%@include file="comuns/taglibs.jsp" %>

<%--TODO criar uma fachada para acessar essa pagina não usar dao na view--%>
<%
    LocatarioDAO locatarioDAO = new LocatarioDAO();
    ArrayList<LocatarioDTO> locatarios = locatarioDAO.listarTodos();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestão de Locatários</title>
        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/gestaoLocatarios.css">
    </head>
    <body>
        <%@include file="comuns/retornarInicial.jsp" %>
        <h1>Gestão de Locatários</h1>
        <main>
            <form action="GestaoLocatarios" method="post">
                <table>
                    <tr>
                        <td>
                            Locatário:
                        </td>
                        <td>
                            <select id="listalocatarios" name='lista'>
                                <% for(int i = 0; i < locatarios.size(); i+=1) { %>   
                                    <option value="<%= i %>"><%= locatarios.get(i) %></option>
                                <% } %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            ID:
                        </td>
                        <td>
                            <input id='IDInput' type='text' name='id' value='<%= locatarios.get(0).getId() %>' readonly>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Nome:
                        </td>
                        <td>
                            <input id='nomeInput' type='text' name='nome' value='<%= locatarios.get(0).getNome() %>' >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            CPF:
                        </td>
                        <td>
                            <input id='CPFInput' type='text' name='cpf' value='<%= locatarios.get(0).getCPF() %>' >
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
                            <input id='emailInput' type='text' name='email' value='<%=locatarios.get(0).getEmail()%>' >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Telefone:
                        </td>
                        <td>
                            <input id='telefoneInput' type='text' name='telefone' value='<%=locatarios.get(0).getTelefone()%>' >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            RG:
                        </td>
                        <td>
                            <input id='RGInput' type='text' name='rg' value='<%=locatarios.get(0).getRG()%>' >
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit" name="submitType" value="adicionar">Adicionar locatário</button>
                            <button type="submit" name="submitType" value="deletar">Deletar locatário</button>
                            <button type="submit" name="submitType" value="atualizar">Atualizar locatário</button>
                        </td>
                    </tr>
                </table> 
            </form>
        </main>
        <script>
            var arrayDeLocatarios = [
                <%
                    for(int i = 0; i < locatarios.size(); i+=1) {
                %>
                {
                    id: <%= locatarios.get(i).getId() %>,
                    nome: '<%= locatarios.get(i).getNome() %>',
                    cpf: '<%= locatarios.get(i).getCPF() %>',
                    senha: '<%= locatarios.get(i).getSenha() %>',
                    email: '<%= locatarios.get(i).getEmail() %>',
                    telefone: '<%= locatarios.get(i).getTelefone() %>',
                    rg: '<%= locatarios.get(i).getRG() %>'
                },
                <%
                    }
                %>
            ];
            
            var listalocatarios = document.getElementById('listalocatarios');

            listalocatarios.addEventListener('change', function() {
                document.getElementById("IDInput").value = arrayDeLocatarios[this.value].id;
                document.getElementById("nomeInput").value = arrayDeLocatarios[this.value].nome;
                document.getElementById("CPFInput").value = arrayDeLocatarios[this.value].cpf;
                document.getElementById("senhaInput").value = '';
                document.getElementById("emailInput").value = arrayDeLocatarios[this.value].email;
                document.getElementById("telefoneInput").value = arrayDeLocatarios[this.value].telefone;
                document.getElementById("RGInput").value = arrayDeLocatarios[this.value].rg;
            });
        </script>        
    </body>
</html>
