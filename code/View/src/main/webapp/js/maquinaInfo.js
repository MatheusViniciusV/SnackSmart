//Botões
let atualizarDadosEl = document.getElementById("atualizarDados");
let botoesCancelarEl = document.querySelectorAll(".cancelar");
//Formularios
let formAddMaquinaEl = document.querySelector("#formAddMaquina");
let formAtualizarMaquinaEl = document.querySelector("#formAtualizarMaquina");
let remocaoMaquinaEl = document.querySelector("#remocaoMaquina");
let informacaoMaquinaEl = document.querySelector("#informacaoMaquina");
let feedbackMaquinaEl = document.querySelector("#feedbackMaquina");
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
let feedbackMaquinaCodigoEl = document.getElementById("feedbackMaquinaCodigo");

var codigoInfoMaquina;
var vetorMaquinaArray; 

var vetorNomes = [];
var vetorCPF = [];
function selectDinamicoLocatario(){
    let locatarioEl = document.getElementById("locatario");
    let novoLocatarioEl = document.getElementById("novoLocatario");
    locatarioEl.innerHTML = "";
    novoLocatarioEl.innerHTML = "";
    for (let i = 0; i < vetorNomes.length; i++){
        let option = document.createElement('option');
        option.value = vetorCPF[i];
        option.textContent = vetorNomes[i];
        novoLocatarioEl.appendChild(option);
        let option2 = document.createElement('option');
        option2.value = vetorCPF[i];
        option2.textContent = vetorNomes[i];
        locatarioEl.appendChild(option2);      
    }
}

function mostrarFormulario(tipoForm){
    let formulario = document.getElementById(tipoForm);
    if (tipoForm === "formAddMaquina" || tipoForm === "formAtualizarMaquina"){
        selectDinamicoLocatario();     
    }
    
    formulario.style.display = "flex"; 
    bloquearConteudoEl.style.display = "block";
}

function fecharFormularios(){
    if (usuarioAcessando === "LOCADOR"){
        formAddMaquinaEl.style.display = "none"; 
        formAtualizarMaquinaEl.style.display = "none"; 
        remocaoMaquinaEl.style.display = "none"; 
    } else {      
        feedbackMaquinaEl.style.display = "none"; 
    }
    informacaoMaquinaEl.style.display = "none"; 
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
    let novoBotaoFeedbackEl = document.createElement("button");
    let novoBreak = document.createElement("br");
    
    
    novoSlot.classList.add("slot");
    novoBotaoInfoEl.classList.add("infoMaquina");
    novoBotaoRemoverEl.classList.add("removerMaquina");
    novoBotaoFeedbackEl.classList.add("enviarFeedback");
    novoBotaoInfoEl.addEventListener("click", recuperaInfoSlotMaquina);
    novoBotaoRemoverEl.addEventListener("click", recuperaInfoSlotMaquina);
    novoBotaoFeedbackEl.addEventListener("click", recuperaInfoSlotMaquina);
    novoBotaoInfoEl.addEventListener("click", ButtonIClick);
    novoBotaoRemoverEl.addEventListener("click", ButtonRClick);
    novoBotaoFeedbackEl.addEventListener("click", ButtonFClick);
    
    novoBotaoFeedbackEl.innerHTML = "Enviar Feedback";
    novoBotaoInfoEl.innerHTML = "Informações da máquina";
    novoBotaoRemoverEl.innerHTML = "Remover Máquina";
    novoh2.innerHTML = nome;
    novoh3.innerHTML = "COD-" + codigo;
    novoP.innerHTML = formatarStatus(status); 
    
    converterBytesEmImagem(img, novaImg);
    novaImg.alt = "Imagem da "+ nome; 
    
    mainEl.insertBefore(novoSlot, articleBeforeEl);
    novoSlot.appendChild(novoh2);
    novoSlot.appendChild(novoh3);
    novoSlot.appendChild(novoP);
    novoSlot.appendChild(novaImg);
    novoSlot.appendChild(novoBreak);  
    novoSlot.appendChild(novoBotaoInfoEl);
    if (usuarioAcessando === "LOCADOR")
        novoSlot.appendChild(novoBotaoRemoverEl);
    else 
        novoSlot.appendChild(novoBotaoFeedbackEl);
  
   let slot = document.getElementById("addMaquinaSlot");
   if (usuarioAcessando === "LOCADOR"){
       slot.remove();
       criarSlotAddMaquina();
   }
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
    maquinaEncontrada = encontrarMaquinaPorCodigo(codigoInfoMaquina, vetorMaquinaArray);
    exibirInformacaoMaquina(maquinaEncontrada.nome, maquinaEncontrada.codigo, formatarStatus(maquinaEncontrada.status), 
    formatarTipo(maquinaEncontrada.tipo), maquinaEncontrada.localizacao, maquinaEncontrada.locatario);
    mostrarFormulario('informacaoMaquina');
}
function ButtonRClick() {
    maquinaEncontrada = encontrarMaquinaPorCodigo(codigoInfoMaquina, vetorMaquinaArray);
    removerMaquinaCodigoEl.value = maquinaEncontrada.codigo;
    mostrarFormulario('remocaoMaquina');
}

function ButtonFClick() {
    maquinaEncontrada = encontrarMaquinaPorCodigo(codigoInfoMaquina, vetorMaquinaArray);
    feedbackMaquinaCodigoEl.value = maquinaEncontrada.codigo;
    mostrarFormulario('feedbackMaquina');
}

function recuperaInfoSlotMaquina() {
   let informacoeSlotMaquina = []; 
   informacoeSlotMaquina[0] = this.parentNode.firstElementChild.innerHTML;
   informacoeSlotMaquina[1] = this.parentNode.children[1].innerHTML;
   informacoeSlotMaquina[2] = this.parentNode.children[2].innerHTML;
   codigoInfoMaquina = informacoeSlotMaquina[1].replace("COD-", "");
   return informacoeSlotMaquina;  
}

function encontrarMaquinaPorCodigo(codigo, maquinas) {
    for (let i = 0; i < maquinas.length; i++) {
        if (maquinas[i].codigo === codigo) {
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

function converterBytesEmImagem(url, imagemEl){
    if (url === "none" || url === ""){
        imagemEl.src = "img/NonePhoto.png"; 
    } else{
        imagemEl.src = "data:image/*;base64," + url;
    }  
}

function Main(){    
    if (usuarioAcessando === "LOCADOR"){
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
            let statusLabelEl = document.getElementById('statusLabel'); 
            let novoLocatarioInputEl = document.getElementById('novoLocatarioInput');
            let locatarioMaquinaEl = document.getElementById("locatarioMaquina");
            let statusMaquinaEl = document.getElementById("statusDinamicoH2");
            statusLabelEl.textContent = "";
            novoLocatarioInputEl.textContent = "";
            let textoCompleto = statusMaquinaEl.textContent;
            let posicaoInicio = textoCompleto.indexOf('Status da Máquina:') + 'Status da Máquina:'.length;
            let parteDoTexto = textoCompleto.substring(posicaoInicio).trim();          
            statusLabelEl.textContent += "Alterar status da máquina:" + "(" + parteDoTexto + ")";
            
            textoCompleto = locatarioMaquinaEl.textContent;
            posicaoInicio = textoCompleto.indexOf('👤Locatário responsável:') + '👤Locatário responsável:'.length;
            parteDoTexto = textoCompleto.substring(posicaoInicio).trim();  
            novoLocatarioInputEl.textContent += "Alterar locatário:" + "(" + parteDoTexto + ")";
            
            retornarCodigo(event.target, "ab");
        });  
    }


    botoesCancelarEl.forEach(function(botao) {
        botao.addEventListener("click", fecharFormularios);
    });
    
    if (usuarioAcessando === "LOCADOR")
        criarSlotAddMaquina();     
}   

Main(); 

function formatarStatus(status) {
    switch (status) {
        case "ALUGADA":
            return "Alugada";
        case "EM_MANUTENCAO":
            return "Em manutenção";
        case "AGUARDANDO_MANUTENCAO":
            return "Aguardando manutenção";
        case "DISPONIVEL":
            return "Disponível";
        case "REMOVIDA":
            return "Removida";
        default:
            return "Status Desconhecido";
    }
}

function formatarTipo(tipo) {
    switch (tipo) {
        case "REFRIGERADA":
            return "Refrigerada";
        case "NAO_REFRIGERADA":
            return "Não refrigerada";
        default:
            return "Status Desconhecido";
    }
}