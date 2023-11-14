
function criarSlotMaquina(nome, codigo, url){  
    let resultadoEl = document.getElementById("resultMaquina");
    let novoSlot = document.createElement("div");
    let novoh2 = document.createElement("h2");
    let novop = document.createElement("p");
    let novaImg = document.createElement("img");
    
    novoSlot.classList.add("slotClick");
    novoh2.innerHTML = nome;
    novop.innerHTML = "COD-" + codigo;

    converterBytesEmImagem(url, novaImg);
    novaImg.alt = "Imagem da "+ nome; 

    resultadoEl.appendChild(novoSlot);
    novoSlot.appendChild(novoh2);
    novoSlot.appendChild(novop);
    novoSlot.appendChild(novaImg);  
    
    novoSlot.addEventListener("click", slotSelecionado);
}

function converterBytesEmImagem(url, imagemEl){
    if (url === "none" || url === ""){
        imagemEl.src = "img/NonePhoto.png"; 
    } else{
        imagemEl.src = "data:image/*;base64," + url;
    }  
}
function mostrarInfo(codigo, nome, locatario, imagem){
    let nomeMaquinaEl = document.getElementById('nomeMaquina');
    let locatarioMaquinaEl = document.getElementById('locatarioMaquina');
    let imagemMaquinaEl = document.getElementById('imagemMaquina');
    nomeMaquinaEl.innerHTML = "Nome da Maquina: " + nome + " - COD-" + codigo;
    locatarioMaquinaEl.innerHTML = "Alugada por: " + locatario;
    imagemMaquinaEl.src = imagem;
}

function slotSelecionado(event){
    if (event.target === this) {    
        let cod = event.target.children[1].innerHTML;
        let url = event.target.children[2].src;
        cod = cod.split('-');
        cod = parseInt(cod[1]);
        for (var i = 0; i < vetorMaquinaArray.length; i++) {
            let objetoAtual = vetorMaquinaArray[i];           
            if (objetoAtual.codigo == cod) {
                mostrarInfo(objetoAtual.codigo, objetoAtual.nome, objetoAtual.locatario, url);
            }
        }
    }
}

function relatorioFeedback(event){
    let str = event.target.parentNode.children[1].children[0].innerHTML;
    let cod = str.match(/COD-(\d+)/)[1];
    for (var i = 0; i < vetorFeedbackArray.length; i++) {
        let objetoAtual = vetorFeedbackArray[i];           
        if (objetoAtual.codigo == cod) {
            mostrarInfo(objetoAtual.codigo, objetoAtual.nome, objetoAtual.locatario, url);
        }
    }
}

let botaoRelatorioEl = document.getElementById('botaoFeedback');
botaoRelatorioEl.addEventListener('click', relatorioFeedback);


