//Botões
let addMaquinaEL = document.querySelector("#addMaquina");
let informacaoMaquinaEl = document.querySelector("#informacaoMaquina");
let remocaoMaquinaEl = document.querySelector("#remocaoMaquina");
let atualizarDadosEl = document.getElementById("atualizarDados");
let botoesCancelarEl = document.querySelectorAll(".cancelar");
//Formularios
let formAddMaquinaEl = document.querySelector("#formAddMaquina");
let formAtualizarMaquinaEl = document.querySelector("#formAtualizarMaquina");
let bloquearConteudoEl = document.getElementById("bloquearConteudo");
//Verificações de CEP
let localizacaoEl = document.getElementById("localizacao");
let localizacaoTextEl = document.getElementById("localizacaoText");
let novaLocalizacaoEl = document.getElementById("novaLocalizacao");
let novaLocalizacaoLabelEl = document.getElementById("novaLocalizacaoLabel");
//Input CSS
let preencherEl = document.querySelectorAll(".preencher");
//Codigo da máquina
let removerMaquinaCodigoEl = document.getElementById("removerMaquinaCodigo");
let atualizarMaquinaCodigoEl = document.getElementById("atualizarMaquinaCodigo");

var codigoInfoMaquina;
var vetorMaquinaArray; 
//Aqui será necessário comunicar com o banco de dados dos locatarios, o que será feito posteriormente
let vetor = ["Nenhum", "Alice", "Duda", "Fernando", "Alemão", "Jó Soares", "Neymar", "Yasmin"];

function selectDinamicoLocatario(vetor){
    let locatarioEl = document.getElementById("locatario");
    let novoLocatarioEl = document.getElementById("novoLocatario");
    locatarioEl.innerHTML = "";
    novoLocatarioEl.innerHTML = "";
    for (let i = 0; i < vetor.length; i++){
        let option = document.createElement('option');
        option.value = vetor[i];
        option.textContent = vetor[i];
        novoLocatarioEl.appendChild(option);
        let option2 = document.createElement('option');
        option2.value = vetor[i];
        option2.textContent = vetor[i];
        locatarioEl.appendChild(option2);      
    }
}

function mostrarFormulario(tipoForm){
    let formulario = document.getElementById(tipoForm);
    if (tipoForm === "formAddMaquina" || tipoForm === "formAtualizarMaquina"){
        selectDinamicoLocatario(vetor);     
    }
    
    formulario.style.display = "flex"; 
    bloquearConteudoEl.style.display = "block";
}

function fecharFormularios(){
    formAddMaquinaEl.style.display = "none"; 
    formAtualizarMaquinaEl.style.display = "none"; 
    informacaoMaquinaEl.style.display = "none"; 
    remocaoMaquinaEl.style.display = "none"; 
    bloquearConteudoEl.style.display = "none";
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
    novoBotao.setAttribute("id", "addMaquinaButton");
    novoLabel.setAttribute("id", "addMaquinaLabel");
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

   
    novoBotaoInfoEl.innerHTML = "Informações da máquina";
    novoBotaoRemoverEl.innerHTML = "Remover Máquina";
    novoh2.innerHTML = nome;
    novoh3.innerHTML = "COD-" + codigo;
    novoP.innerHTML = status; 
    
    converterBytesEmImagem(img, novaImg);
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
                resposta.textContent = "CEP válido: ";
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

function exibirInformacaoMaquina(nomeMaquina, codeMaquina, statusMaquina, tipoMaquina, LocalizacaoDaMaquina, locatarioResponsavel){
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

function ButtonIClick() {
    try {
        maquinaEncontrada = encontrarMaquinaPorCodigo(codigoInfoMaquina, vetorMaquinaArray);
        if (maquinaEncontrada !== null) {
            exibirInformacaoMaquina(maquinaEncontrada.nome, maquinaEncontrada.codigo, maquinaEncontrada.status, 
            maquinaEncontrada.tipo, maquinaEncontrada.localizacao, maquinaEncontrada.locatario);
        }
    } catch { 
        console.log("não foi possível exibir");
        exibirInformacaoMaquina("Maquina Não Definida", 0000, "Não definido",
    "Refrigerada", "Rua José de Bessas, Venda Nova, Belo Horizonte, MG - 93084-111", "Waldir Braz");//FUNÇAO TESTE
    }
    mostrarFormulario('informacaoMaquina');
}
function ButtonRClick() {
    mostrarFormulario('remocaoMaquina');
}

function recuperaInfoSlotMaquina() {
   let informacoeSlotMaquina = []; 
   informacoeSlotMaquina[0] = this.parentNode.firstElementChild.innerHTML;
   informacoeSlotMaquina[1] = this.parentNode.children[1].innerHTML;
   informacoeSlotMaquina[2] = this.parentNode.children[2].innerHTML;
   codigoInfoMaquina = informacoeSlotMaquina[1].replace("COD-", "");
   return informacoeSlotMaquina; 
}

function encontrarMaquinaPorCodigo(codigoInfoMaquina, maquinas) {
    for (let i = 0; i < maquinas.length; i++) {
        if (maquinas[i].codigo === codigoInfoMaquina) {
            return maquinas[i];
        }
    }
    return null; 
} 

function retornarCodigo(botao, str) {
    if (str === "rb"){
        let pai = botao.parentNode;
        let strCodigo = pai.children[1].textContent; 
        let codigo = strCodigo.slice(strCodigo.indexOf("-") + 1);
        removerMaquinaCodigoEl.value = codigo;
        return codigo;
    } else {
        let avo = botao.parentNode.parentNode;
        let strCodigo = avo.children[1].textContent; 
        let codigo = strCodigo.slice(strCodigo.indexOf("-") + 1);
        atualizarMaquinaCodigoEl.value = codigo;
        return codigo;
    }
}

function converterBytesEmImagem(bytes, imagemEl){
    try {
        let byteCharacters = atob(bytes);
        let byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        let byteArray = new Uint8Array(byteNumbers);
        let blob = new Blob([byteArray], { type: 'image/png' }); 
        let url = URL.createObjectURL(blob);

        imagemEl.src = url;
    } catch(err){
        imagemEl.src = "img/NonePhoto.png";
    }  
}


preencherEl.forEach(function(botao) {
    botao.addEventListener("blur", verificaPreenchido);
});

localizacaoEl.addEventListener("blur", function() {
    buscarCEP(localizacaoEl, localizacaoTextEl);
});

novaLocalizacaoEl .addEventListener("blur", function() {
    buscarCEP(novaLocalizacaoEl, novaLocalizacaoLabelEl);
});

atualizarDadosEl.addEventListener("click", function(event) {
    fecharFormularios();
    mostrarFormulario('formAtualizarMaquina'); 
    retornarCodigo(event.target, "ab");
});;

botoesCancelarEl.forEach(function(botao) {
    botao.addEventListener("click", fecharFormularios);
});



function Main(){
    
    let removerMaquinaEl = document.querySelectorAll(".removerMaquina");
    removerMaquinaEl.forEach(function(botao) {
        botao.addEventListener("click", function(event){
            retornarCodigo(event.target, "rb");
        });
    });
    let infoMaquinaEl = document.querySelectorAll(".infoMaquina");
    infoMaquinaEl.forEach(function(botao) {
        botao.addEventListener("click", function(event){
            retornarCodigo(event.target, "rb");
        });
    });
    
    let maquina1 = {
        nome: "Máquina de Salgadinhos",
        cod: 3372,
        status: "Aguardando manutenção",
        imagem: "img/NonePhoto.png"
    };
    let maquina2 = {
        nome: "Máquina Coca-Cola",
        cod: 4322,
        status: "Em manutenção",
        imagem: "img/NonePhoto.png"
    };
    let maquina3 = {
        nome: "Promotional Machine",
        cod: 3334,
        status: "Disponível",
        imagem: "img/NonePhoto.png"
    };
    let maquina4 = {
        nome: "Máquina de Refrigerantes",
        cod: 9302,
        status: "Em funcionamento",
        imagem: "img/NonePhoto.png"
    };
    
    let Vet = [maquina1, maquina2, maquina3, maquina4];
    //Vet.forEach((maquina) => criarSlotMaquina(maquina.nome, maquina.cod, maquina.status, maquina.imagem)); //TESTE;
    criarSlotAddMaquina();
}   

Main(); 

