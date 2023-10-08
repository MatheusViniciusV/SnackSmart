<%-- 
    Document   : exemploSo
    Created on : 8 de out. de 2023, 11:23:15
    Author     : eloym
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Gestão de Máquinas</title>
    </head>
    <body>
        <%@include file="comuns/retornarInicial.jsp" %>
        <h1>Máquinas</h1>
        <main id="gestaoMaquinasMain">
            <div>
                <h2>Máquina 01</h2>
                <h3>COD-001</h3>
                <img src="src" alt="alt"/>
                <p>Status</p>
                <button>Informações de contrato</button>
                <button>Remover Máquina</button>
            </div>
            
            <div>
                <p>Localizacao: </p> 
                <p>Locatario responsavel: </P> 
                <p>Status</p>
            </div>
            
            <div>
                <button>Adicionar nova máquina</button>
            </div>
            
            <div>
                <h2>Adicionar nova máquina</h2>
                <p>Preencha todos os campos abaixos<p>
                <form action="" method="post" enctype="multipart/form-data">
                    <label for="nome">Nome:</label>
                    <input type="text" id="nome" name="nome" required><br>

                    <label for="tipo">Tipo:</label>
                    <select id="tipo" name="tipo" required>
                        <option value="Salgados">Salgados</option>
                        <option value="Bebidas">Bebidas</option>
                        <option value="Lanches">Lanches</option>
                    </select><br>

                    <label for="imagem">Imagem:</label>
                    <input type="file" id="imagem" name="imagem" accept="image/*" required><br>

                    <label for="localizacao">Localização:</label>
                    <input type="text" id="localizacao" name="localizacao" required><br>

                    <input type="submit" value="Enviar">
                </form>
            </div>
        </main>
    </body>
</html>
