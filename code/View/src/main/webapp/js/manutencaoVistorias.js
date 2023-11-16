function criarNotificacao(codigo, titulo, mensagem){  
    let notificacoesEl = document.getElementById("notificacoes");
    let novoSlot = document.createElement("div");
    let codigoEl = document.createElement("p");
    let tituloEl = document.createElement("p");
    let mensagemEl = document.createElement("p");
    
    novoSlot.classList.add("mensagem");
    codigoEl.innerHTML = codigo;
    tituloEl.innerHTML = titulo;
    mensagemEl.innerHTML = mensagem;

    notificacoesEl.appendChild(novoSlot);
    novoSlot.appendChild(codigoEl);
    novoSlot.appendChild(tituloEl);
    novoSlot.appendChild(mensagemEl);  
}

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
function mostrarFormFeedback (feedback){
    let novop1 = document.createElement("p");
    let novop2 = document.createElement("p");
    let novop3 = document.createElement("p");
    let novop4 = document.createElement("p");
    let feedbackRetornadoEl = document.getElementById('feedbackRetornado');
    
    novop1.innerHTML = feedback.codigo;
    novop2.innerHTML = feedback.titulo;
    novop3.innerHTML = feedback.mensagem;
    novop4.innerHTML = feedback.tipo;
    
    feedbackRetornadoEl.appendChild(novop1);
    feedbackRetornadoEl.appendChild(novop2);
    feedbackRetornadoEl.appendChild(novop3);
    feedbackRetornadoEl.appendChild(novop4);   
}

function relatorioFeedback(event){
    let str = event.target.parentNode.children[1].children[0].innerHTML;
    let cod = str.match(/COD-(\d+)/)[1];
    for (var i = 0; i < vetorFeedbackArray.length; i++) {
        let objetoAtual = vetorFeedbackArray[i];           
        if (objetoAtual.codigo == cod) {
            mostrarFormFeedback(objetoAtual);
        }
    }
}

function mostrarFormAgenda(){ 
    let formVistoriaEl = document.getElementById('formVistoria');
    formVistoriaEl.style.display = "grid";
}
function fecharFormAgenda(){
    let formVistoriaEl = document.getElementById('formVistoria');
    formVistoriaEl.style.display = "none";
}

function pesquisarElementos(){
    let stringProcura = this.value;
    let slots = document.querySelectorAll('.slotClick');
    
    slots.forEach(function(slot) {
        let nome = slot.children[0].innerHTML;
        if (!nome.toLowerCase().includes(stringProcura.toLowerCase()) && stringProcura !== "") {
            slot.style.display = 'none';
        } else 
            slot.style.display = 'flex';
    });
}

let botaoRelatorioEl = document.getElementById('botaoFeedback');
botaoRelatorioEl.addEventListener('click', relatorioFeedback);

let botaoAgendaEl = document.getElementById('botaoAgenda');
botaoAgendaEl.addEventListener('click', mostrarFormAgenda);


let cancelarEl = document.getElementById('cancelar');
cancelarEl.addEventListener('click', fecharFormAgenda);

let buscaEl = document.getElementById('busca');
buscaEl.addEventListener('input', pesquisarElementos);