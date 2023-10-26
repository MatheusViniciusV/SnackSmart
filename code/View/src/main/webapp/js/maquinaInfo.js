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
let preencherEl = document.querySelectorAll(".preencher");

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
    exibirInfoContratoMaquina("Maquina de Salgados", 3302, "Em funcionamento",
    "Refrigerada", "Rua Castelo de Arraiolos, Castelo, Belo Horizonte, MG - 31330-070", "Waldir Braz");//FUNÇAO TESTE
    //Será necessário criar uma conexão com o banco de dados para a passagem dos parâmetros
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

function buscarCEP(cep, resposta) {
    let cepEl = cep; 
    cep = cep.value.replace(/\D/g, '');
    if (cep.length !== 8) {
        resposta.textContent = "CEP inválido.";
        cepEl.value = "";
        cepEl.style.border = "1px solid red";
        return;
    }
    const url = `https://viacep.com.br/ws/${cep}/json/`;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.erro) {
                resposta.textContent = "CEP não encontrado.";
                cepEl.value = "";
                cepEl.style.border = "1px solid red";
            } else {               
                resposta.textContent = "Localização (CEP): ";
                cepEl.value = `${data.logradouro}, ${data.bairro}, ${data.localidade}, ${data.uf} - ${data.cep}`;
                cepEl.style.border = "1px solid black";
            }
        })
        .catch(error => {
            console.error('Erro ao buscar o CEP:', error);
            cepEl.value = "";
            cepEl.style.border = "1px solid red";
        });
}

function verificaPreenchido (event){
    let inputSelecionado = event.target;
    if (inputSelecionado.value === ""){
        inputSelecionado.style.border = "1px solid red";
    } 
    else {
        inputSelecionado.style.border = "1px solid black";
    }
}

function exibirInfoContratoMaquina(nomeMaquina, codeMaquina, statusMaquina, tipoMaquina, LocalizacaoDaMaquina, locatarioResponsavel){
    let nomeMaquinaEl = document.getElementById("nomeMaquina");
    let codeMaquinaEl = document.getElementById("codeMaquina");
    let statusMaquinaEl = document.getElementById("statusDinamicoH2");
    let tipoMaquinaEl = document.getElementById("tipoMaquina");
    let LocalizacaoDaMaquinaEl = document.getElementById("LocalizacaoDaMaquina");
    let locatarioMaquinaEl = document.getElementById("locatarioMaquina");
    
    nomeMaquinaEl.textContent = nomeMaquina;
    codeMaquinaEl.textContent = "COD-" + codeMaquina;
    statusMaquinaEl.textContent = "Status da Máquina: " + statusMaquina;
    tipoMaquinaEl.textContent = "Tipo da Máquina: " + tipoMaquina;
    LocalizacaoDaMaquinaEl.textContent = "📍Localização: " + LocalizacaoDaMaquina;
    locatarioMaquinaEl.textContent = "👤Locatário responsável: " + locatarioResponsavel;
}

preencherEl.forEach(function(botao) {
    botao.addEventListener("blur", verificaPreenchido);
});

localizacaoEl.addEventListener("blur", function() {
    buscarCEP(localizacaoEl, localizacaoTextEl);
});



function Main(){
    let maquina1 = {
        nome: "Máquina de Salgadinhos",
        cod: 3372,
        status: "Aguardando manutenção",
        imagem: "Ceara.png"
    };
    let maquina2 = {
        nome: "Máquina Coca-Cola",
        cod: 4322,
        status: "Em manutenção",
        imagem: "Ceara.png"
    };
    let maquina3 = {
        nome: "Promotional Machine",
        cod: 3334,
        status: "Disponível",
        imagem: "Ceara.png"
    };
    let maquina4 = {
        nome: "Máquina de Refrigerantes",
        cod: 9302,
        status: "Em funcionamento",
        imagem: "Ceara.png"
    };
    
    let VetorDeObjetoMaquinas = [maquina1, maquina2, maquina3, maquina4];
    VetorDeObjetoMaquinas.forEach((maquina) => criarSlotMaquina(maquina.nome, maquina.cod, maquina.status, maquina.imagem));
    //Conexão com o banco de dados para criar cada slot
   
}   

Main(); 