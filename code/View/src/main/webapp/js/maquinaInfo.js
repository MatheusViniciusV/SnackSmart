let addMaquinaEL = document.querySelector(".addMaquina");
console.log('bingos');
addMaquinaEL.addEventListener('click', function(){
    console.log('bingos');
    let parent = this.parentNode;
    parent.innerHtml = `<h2>Adicionar nova máquina</h2>
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
                </form>`
});