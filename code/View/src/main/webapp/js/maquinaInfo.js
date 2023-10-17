//Buttons 
let addMaquinaEL = document.querySelector("#addMaquina");
let infomaquinaEl = document.querySelector(".infomaquinaEl");
let removerMaquinaEl = document.querySelector(".removerMaquinaEl");

let formAddMaquinaEl = document.querySelector("#formAddMaquinaEl");
let infoContratoMaquinaEl = document.querySelector("#infoContratoMaquina");
let remocaoMaquinaEl = document.querySelector("#remocaoMaquinaEl");

addMaquinaEL.addEventListener('click', function(){
    formAddMaquinaEl.style.display = "inherit;"; 
});

infomaquinaEl.addEventListener('click', function(){
    infoContratoMaquinaEl.style.display = "inherit;";    
});

removerMaquinaEl.addEventListener('click', function(){
    remocaoMaquinaEl.style.display = "inherit;";    
});


function criarSlotMaquina(nome, codigo, status, img){
    let novoSlot = document.createElement("div");
    let novoh2 = document.createElement("h2");
    let novoh3 = document.createElement("h3");
    let novoP = document.createElement("p");
    let novaImg = document.createElement("img");
    
    novoSlot.classList.add("slot");
    novoh2.innerHTML = nome;
    novoh3.innerHTML = "COD-" + codigo;
    novoP.innerHTML = "Status: " + status; 
    novaImg.src = img; 
    novaImg.alt = "Imagem da "+ nome; 
    
    novoSlot.appendChild(novoh2);
    novoSlot.appendChild(novoh3);
    novoSlot.appendChild(novoP);
    novoSlot.appendChild(novaImg);
}