function  mostrarFormulário(tipoForm){
    tipoForm.style.display = "inherit;"; 
}

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

//Buttons 
let addMaquinaEL = document.querySelector("#addMaquina");
let infomaquinaEl = document.querySelectorAll(".infoMaquina");
let removerMaquinaEl = document.querySelectorAll(".removerMaquina");

let formAddMaquinaEl = document.querySelector("#formAddMaquina");
let infoContratoMaquinaEl = document.querySelector("#infoContratoMaquina");
let remocaoMaquinaEl = document.querySelector("#remocaoMaquina");

addMaquinaEL.addEventListener('click', mostrarFormulário(formAddMaquinaEl));

infomaquinaEl.forEach(button => {
    button.addEventListener("click", function() {
        mostrarFormulário(infoContratoMaquinaEl);
    });
});

removerMaquinaEl.forEach(button => {
    button.addEventListener("click", function() {
        mostrarFormulário(remocaoMaquinaEl);
    });
});



