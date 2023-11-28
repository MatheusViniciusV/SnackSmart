function criarSlotMaquina(nome, codigo, url){  
    let resultadoEl = document.getElementById("resultMaquina");
    let novoSlot = document.createElement("div");
    let novoh2 = document.createElement("h2");
    let novaImg = document.createElement("img");
    
    novoSlot.classList.add("slotClick");
    novoh2.innerHTML = nome + '<br>' + "COD_" + codigo;;

    converterBytesEmImagem(url, novaImg);
    novaImg.alt = "Imagem da "+ nome; 

    resultadoEl.appendChild(novoSlot);
    novoSlot.appendChild(novaImg);
    novoSlot.appendChild(novoh2);
      
    
    novoSlot.addEventListener("click", slotSelecionado);
}

function mostrarInfo(codigo, nome, locatario, imagem){
    let nomeMaquinaEl = document.getElementById('nomeMaquina');
    let locatarioMaquinaEl = document.getElementById('locatarioMaquina');
    let imagemMaquinaEl = document.getElementById('imagemMaquina');
    nomeMaquinaEl.innerHTML = "Nome da Maquina:" + '<br>' + nome + '<br>' +"COD-" + codigo;
    if (locatario === "")
        locatario = "Ninguém";
    locatarioMaquinaEl.innerHTML = "Alugada por:" + '<br>' + locatario;
    imagemMaquinaEl.src = imagem;
}

function slotSelecionado(){
    let cod = this.children[1].innerHTML;
    let url = this.children[0].src;
    cod = cod.split('_');
    cod = parseInt(cod[1]);
    for (var i = 0; i < vetorMaquinaArray.length; i++) {
        let objetoAtual = vetorMaquinaArray[i];           
        if (objetoAtual.codigo == cod) {
            mostrarInfo(objetoAtual.codigo, objetoAtual.nome, objetoAtual.locatario, url);
        }
    }
    
}

function converterBytesEmImagem(url, imagemEl){
    if (url === "none" || url === ""){
        imagemEl.src = "img/NonePhoto.png"; 
    } else{
        imagemEl.src = "data:image/*;base64," + url;
    }  
}

function pesquisarElementos(){
    let stringProcura = this.value;
    let slots = document.querySelectorAll('.slotClick');
    
    slots.forEach(function(slot) {
        let nome = slot.children[1].innerHTML;
        if (!nome.toLowerCase().includes(stringProcura.toLowerCase()) && stringProcura !== "") {
            slot.style.display = 'none';
        } else 
            slot.style.display = 'flex';
    });
}

let buscaEl = document.getElementById('busca');
buscaEl.addEventListener('input', pesquisarElementos);