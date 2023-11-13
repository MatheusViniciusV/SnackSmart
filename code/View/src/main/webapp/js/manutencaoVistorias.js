
function criarSlotMaquina(maquinasArray){
    maquinasArray.forEach(function(maquina) {
        
    let menuEl = document.getElementById("menu");
    let novoSlot = document.createElement("div");
    let novoh2 = document.createElement("h2");
    let novoh3 = document.createElement("h3");
    let novaImg = document.createElement("img");
    
    novoSlot.classList.add("slot"); 
    
    novoh2.innerHTML = maquina.nome;
    novoh3.innerHTML = "COD-" + maquina.codigo;
    
    converterBytesEmImagem(maquina.img, novaImg);
    novaImg.alt = "Imagem da "+ maquina.nome; 
    
    menuEl.insertBefore(novoSlot);
    novoSlot.appendChild(novoh2);
    novoSlot.appendChild(novoh3);
    novoSlot.appendChild(novaImg);  
    }); 
}

let inputPesquisaEl = $('#termoPesquisa');
inputPesquisaEl.on('input',function(){
     let maquinasArray =
     maquinasArray.forEach(function(maquina) {
        
    });
});


