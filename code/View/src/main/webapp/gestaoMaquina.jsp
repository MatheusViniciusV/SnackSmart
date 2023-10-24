<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/gestaomaquina.css">
        <title>Gestão de Máquinas</title>
    </head>
    <body>
        <%@include file="comuns/retornarInicial.jsp" %>
        <h1 id="titulo">Máquinas</h1>
        <div id="blocker"></div>
        <main id="gestaoMaquinasMain">                                 
            <div class="slot" id="addMaquinaSlot">
                <label id="addMaquinaL">Adicionar nova máquina</label>
                <button id="addMaquina">+</button>
            </div>
                        
            <article id="formAddMaquina">               
                <form action="GestaoMaquina" method="post" enctype="multipart/form-data">
                    <h1 id="tituloForm">Adicionar nova máquina</h1>
                    <h2 id="subtituloForm">Preencha todos os campos abaixos</h2>
                    <label id="nomeDaMaquina" for="nome">Nome da Máquina:</label>
                    <input type="text" id="nome" name="nome" required><br>

                    <label id="tipoInput" for="tipo">Tipo da máquina:</label>                       
                    <select id="tipo" name="tipo" required>
                        <option value="refrigerada">Refrigerada</option>
                        <option value="nãoRefrigerada">Não refrigerada</option>
                    </select><br>
                    
                    <label id="labelMaquina" class="imagem" for="imagem">Foto da máquina:</label>
                    <input id="inputMaquina" type="file" class="imagem" name="imagem" required ><br>
                    
                    <label id="locatarioInput" for="locatario">Locatário responsável:</label>
                    <select id="locatario" name="locatario" required>
                    </select><br>  
                
                    <label id="localizacaoText" for="localizacao">Localização:</label>
                    <input type="text" id="localizacao" name="localizacao" required><br>
                    
                    <input id="enviarformAddMaquina"class="botaoForm" type="submit" value="Enviar">
                    <button id="cancelarformAddMaquina" class="botaoForm"type="cancel" onclick="window.location='gestaoMaquina.jsp';">Cancelar</button>
                </form>
            </article>
                
            <article id="infoContratoMaquina"> 
                <h1 id="nomeMaquina">Máquina 01</h1>
                <h1 id="codeMaquina">COD-001</h1>
                <h2>👤Locatário responsável: Geraldo Azeved</h2> 
                <h2>📍Localização: Bahia, Salvador</h2>              
                <h2 id="statusDinamicoH2">Status da Máquina: Disponível</h2> 
                
                <label>Alterar status da máquina:
                <select id="status" name="status" onchange="mudarH2()" required >
                    <option value="Disponível">Disponível</option>
                    <option value="Em funcionamento">Em funcionamento</option>
                    <option value="Em manutenção">Em manutenção</option>
                    <option value="Aguardando manutenção">Aguardando manutenção</option>                       
                </select><br>  
                </label>
                <div class="botoesForm">
                    <input class="botaoForm" type="submit" value="Concluído">
                    <button class="botaoForm" type="cancel" onclick="window.location='gestaoMaquina.jsp';">Cancelar</button>
                </div>
                    
            </article>
            
            <article id="remocaoMaquina">
                <h1>Você tem certeza?</h1>
                <h2>Ao remover a máquina, todos os dados relacionados a ela serão excluídos!</h2>
                <p>⚠ Dados relacionados à máquina e ao locatário não poderão serem acessados posteriormente.
                    Caso a máquina esteja ligada a algum cliente é importante que o locatário esteja ciente disso.</p>
                <div class="botoesForm">
                    <input class="botaoForm" type="submit" value="REMOVER MÁQUINA">
                    <button class="botaoForm" type="cancel" onclick="window.location='gestaoMaquina.jsp';">CANCELAR</button>
                </div>
            </article>
            
        </main>
        <%@include file="comuns/jqueryLink.jsp" %>
        <script src="js/maquinaInfo.js"></script>
        
    </body>
</html>
