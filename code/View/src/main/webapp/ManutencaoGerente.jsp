<%@ page import= "br.cefetmg.snacksmart.dao.MaquinaDAO" %>
<%@ page import= "br.cefetmg.snacksmart.dto.MaquinaDTO" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/manutencao.css">
        <link rel="stylesheet" href="css/Calendario.css">

    </head>

    <body>
        <form id="formVistoria">
            <p>Descrição</p>
            <input type="text"></input>
            <p>Data</p>
            <input type="date">
            <input type="submit">
            
        </form>
        <%@include file="comuns/retornarInicial.jsp" %>

        <div id="topo">
            <h1 id="titulo">Manutenções e vistorias</h1>
        </div>

        <div class="conteudo">
            <div class="box">
                <input type="text" value="procurar máquina" id="busca"></input>
                <% MaquinaDAO maq = new MaquinaDAO();
                   MaquinaDTO selecionada = maq.acessarMaquina();
                %>

            </div>
            <div class="box-info">
                <img id="imagemMaquina" src="" alt="">
                <div>
                    <p id="nomeMaquina"><%= selecionada.getNome()%></p>
                    <p id="locatarioMaquina">alugada por: <%= selecionada.getLocatario()%></p>
                </div>
                <input type="button" id="botaoRelatorio" onClick ="document.getElementById('formVistoria').display ='none' " value="Agendar vistoria"></input>
                <input type="button" id="botaoRelatorio" onClick ="document.location.href = ''" value="Verificar Feedbacks"></input>

            </div>

        </div>
    </div>
    <br>
    <div class="conteudo">
        <%@include file="comuns/Calendario.jsp" %>

    </div>
        <script src="js/ManutencaoInfo.js"></script>
</body>

</html>