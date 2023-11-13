<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/manutencao.css">
        <link rel="stylesheet" href="css/Calendario.css">

    </head>

    <body>
        <%@include file="comuns/retornarInicial.jsp" %>

        <div id="topo">
            <h1 id="titulo">Manutenções e vistorias</h1>
        </div>

        <div class="conteudo">
            <div class="box">
                <input type="text" value="procurar máquina" id="busca"></input>

            </div>
            <div class="box-info">
                <img src="" alt="">
                <div>
                    <p>máquina 1</p>
                    <p>alugada por: </p>
                </div>
                <input type="button" id="botaoRelatorio" onClick ="document.location.href = '/locadora/controller?pagina=EmitirRelatorio'" value="Agendar vistoria"></input>
                <input type="button" id="botaoRelatorio" onClick ="document.location.href = '/locadora/controller?pagina=EmitirRelatorio'" value="Verificar Feedbacks"></input>

            </div>

        </div>
    </div>
    <br>
    <div class="conteudo">
        <%@include file="comuns/Calendario.jsp" %>

    </div>
</body>

</html>