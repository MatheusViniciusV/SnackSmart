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
        <main id="gestaoMaquinasMain">
            
            <div class="slot">
                <h2>Máquina 01</h2>
                <h3>COD-001</h3>
                <p>Status: null</p>
                <img src="src" alt="alt"/><br>
                <button id="infomaquina">Informações de contrato</button>
                <button id="remover">Remover Máquina</button>
            </div>
            
            <div class="slot">
                <p>Localizacao: </p> 
                <p>Locatario responsavel: </P> 
                <p>Status: null</p>
            </div>
            
            
            <div class="slot">
                <h2>Adicionar nova máquina</h2>
                <p>Preencha todos os campos abaixos<p>
                <form action="GestaoMaquina" method="post" enctype="multipart/form-data">
                    <label for="nome">Nome:</label>
                    <input type="text" id="nome" name="nome" required><br>

                    <label for="tipo">Tipo:</label>
                    <select id="tipo" name="tipo" required>
                        <option value="Salgados">Salgados</option>
                        <option value="Bebidas">Bebidas</option>
                        <option value="Lanches">Lanches</option>
                    </select><br>

                    <label for="imagem">Imagem:</label>
                    <input type="file" id="imagem" name="imagem" required><br>

                    <label for="localizacao">Localização:</label>
                    <input type="text" id="localizacao" name="localizacao" required><br>

                    <input type="submit" value="Enviar">
                </form>
            </div>
         
           
            
            <div class="slot">
                <p><p>
                <button class="addMaquina">Adicionar nova máquina</button>
            </div>
        </main>
        
        <%@include file="comuns/jqueryLink.jsp" %>
        <script src="js/maquinaInfo.js"></script>
    </body>
</html>
