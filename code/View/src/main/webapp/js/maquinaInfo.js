//Botões  
let vetor = ["Nenhum", "Alice", "Duda", "Fernando", "Alemão", "Jó Soares", "Neymar", "Yasmin"];
let addMaquinaEL = document.querySelector("#addMaquina");
let infomaquinaEl = document.querySelectorAll(".infoMaquina");
let removerMaquinaEl = document.querySelectorAll(".removerMaquina");
let formAddMaquinaEl = document.querySelector("#formAddMaquina");
let infoContratoMaquinaEl = document.querySelector("#infoContratoMaquina");
let remocaoMaquinaEl = document.querySelector("#remocaoMaquina");
let blockerEl = document.getElementById("blocker");
let localizacaoEl = document.getElementById("localizacao");
let localizacaoTextEl = document.getElementById("localizacaoText");
    
function  mostrarFormulario(tipoForm){
    let formulario = document.getElementById(tipoForm);
    if (tipoForm === "formAddMaquina"){
        selectDinamicoLocatario(vetor);     //Aqui será necessário comunicar com o banco de dados
    }
    formulario.style.display = "flex"; 
    blockerEl.style.display = "block";
}

function mudarH2() {
    let statusEl = document.getElementById("status");
        let statusDinamicoH2El = document.getElementById("statusDinamicoH2");
        let opcaoSelecionada = statusEl.value;

        switch (opcaoSelecionada) {
            case "Disponível":   
                statusDinamicoH2El.innerHTML = "Status da Máquina: Disponível";  
                break;
            case "Em funcionamento":
                statusDinamicoH2El.innerHTML = "Status da Máquina: Em funcionamento";
                break;
            case "Em manutenção":
                statusDinamicoH2El.innerHTML = "Status da Máquina: Em manutenção";
                break;
            default:
                statusDinamicoH2El.innerHTML = "Status da Máquina: Aguardando manutenção";
        }
}

function selectDinamicoLocatario(vetor){
    let locatarioEl = document.getElementById("locatario");
    for (let i = 0; i < vetor.length; i++){
        let option = document.createElement('option');
        option.value = vetor[i];
        option.textContent = vetor[i];
        locatarioEl.appendChild(option); 
    }
}

function ButtonIClick() {
    mostrarFormulario('infoContratoMaquina');
}
function ButtonRClick() {
    mostrarFormulario('remocaoMaquina');
}

function criarSlotMaquina(nome, codigo, status, img){
    let mainEl = document.getElementById("gestaoMaquinasMain");
    let articleBeforeEl = document.getElementById("formAddMaquina"); 
    let novoSlot = document.createElement("div");
    let novoh2 = document.createElement("h2");
    let novoh3 = document.createElement("h3");
    let novoP = document.createElement("p");
    let novaImg = document.createElement("img");
    let novoBotaoInfoEl = document.createElement("button");
    let novoBotaoRemoverEl = document.createElement("button");
    let novoBreak = document.createElement("br");
    
    
    novoSlot.classList.add("slot");
    novoBotaoInfoEl.classList.add("infoMaquina");
    novoBotaoRemoverEl.classList.add("removerMaquina");
    
    novoBotaoInfoEl.addEventListener("click", ButtonIClick);
    novoBotaoRemoverEl.addEventListener("click", ButtonRClick);

   
    novoBotaoInfoEl.innerHTML = "Informações de contrato";
    novoBotaoRemoverEl.innerHTML = "Remover Máquina";
    novoh2.innerHTML = nome;
    novoh3.innerHTML = "COD-" + codigo;
    novoP.innerHTML = status; 
    novaImg.src = img; 
    novaImg.alt = "Imagem da "+ nome; 
    
    mainEl.insertBefore(novoSlot, articleBeforeEl);
    novoSlot.appendChild(novoh2);
    novoSlot.appendChild(novoh3);
    novoSlot.appendChild(novoP);
    novoSlot.appendChild(novaImg);
    novoSlot.appendChild(novoBreak);  
    novoSlot.appendChild(novoBotaoInfoEl);
    novoSlot.appendChild(novoBotaoRemoverEl);
   
   let slot = document.getElementById("addMaquinaSlot");
   slot.remove();
   criarSlotAddMaquina();
   let infoEl = document.querySelectorAll(".infoMaquina");
   infoEl.forEach(function(botao) {
    botao.addEventListener("click", recuperaInfoSlotMaquina);
   });
}

function criarSlotAddMaquina(){
    let mainEl = document.getElementById("gestaoMaquinasMain"); 
    let articleBeforeEl = document.getElementById("formAddMaquina"); 
    
    let novoSlot = document.createElement("div");
    let novoLabel = document.createElement("label");
    let novoBreak = document.createElement("br");
    let novoBotao = document.createElement("button");
    
    novoSlot.classList.add("slot");
    novoSlot.setAttribute("id", "addMaquinaSlot");
    novoBotao.setAttribute("id", "addMaquina");
    novoLabel.setAttribute("id", "addMaquinaL");
    function ButtonClick() { 
        mostrarFormulario('formAddMaquina');
    }
    novoBotao.addEventListener("click", ButtonClick); 
    novoBotao.innerHTML = "+";
    novoLabel.innerHTML = "Adicionar nova máquina";
    mainEl.insertBefore(novoSlot, articleBeforeEl);
    novoSlot.appendChild(novoLabel);
    novoSlot.appendChild(novoBotao);
    novoSlot.appendChild(novoBreak);  
}

function recuperaInfoSlotMaquina() {
   let informacoeSlotMaquina = []; 
   informacoeSlotMaquina[0] = this.parentNode.firstElementChild.innerHTML;
   informacoeSlotMaquina[1] = this.parentNode.children[1].innerHTML;
   informacoeSlotMaquina[2] = this.parentNode.children[2].innerHTML;
   return informacoeSlotMaquina; 
}

function validarEndereco(input, label) {
    let apiKey = 'f93f6ab5e3bc485dbd4afd9a9036f5dd'; 
    let endereco = input.value;    
    let enderecoBrasil = `${encodeURIComponent(endereco)}, Brasil`;
    let url = `https://api.opencagedata.com/geocode/v1/json?q=${enderecoBrasil}&key=${apiKey}`;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.results.length > 0) {
                input.style.border = "black 2px solid";
                let resultado = data.results[0];
                label.textContent = "Localização:";               
                input.value = resultado.formatted;   
                if (resultado.components.postcode) {
                    input.style.border = "red 2px solid";
                    label.textContent = "Localização Inválida";               
                    input.value = ""; 
                } 
            } 
            if (input.value === "Brasil"){
                input.style.border = "red 2px solid";
                label.textContent = "Localização Inválida";               
                input.value = ""; 
            }            
        })
        .catch(error => {
            console.error("Erro ao validar o endereço:", error);
        });
} 


localizacaoEl.addEventListener("blur", function() {
    validarEndereco(localizacaoEl, localizacaoTextEl);
});


function Main(){
    criarSlotMaquina("Máquina de Salgadinhos", 372, "Aguardando manutenção", "Ceara.png"); //FUNÇAO TESTE
    criarSlotMaquina("KTO", 69, "Em manutenção", "Ceara.png"); //FUNÇAO TESTE
    criarSlotMaquina("Corinthians", 24, "Disponível", "Ceara.png"); //FUNÇAO TESTE
    criarSlotMaquina("Corinthians", 24, "Disponível", "Ceara.png"); //FUNÇAO TESTE
}   

Main(); 