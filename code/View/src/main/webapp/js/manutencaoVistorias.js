function criarNotificacao(codigo, titulo, mensagem){  
    let notificacoesEl = document.getElementById("notificacoes");
    let novoSlot = document.createElement("div");
    let codigoEl = document.createElement("h2");
    let tituloEl = document.createElement("p");
    let mensagemEl = document.createElement("p");
    
    novoSlot.classList.add("mensagem");
    codigoEl.innerHTML = "COD_" + codigo;
    tituloEl.innerHTML = "Assunto: " + titulo;
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
function mostrarFormFeedback (feedback){
    let divEl = document.createElement("div");
    divEl.style.overflow = "auto";
    let concluirEl = document.createElement("input");
    let hiddenEl = document.createElement("input"); 
    hiddenEl.name = "removerFeedback";  
    hiddenEl.type = "text";
    hiddenEl.value = feedback.titulo;
    hiddenEl.hidden = true;
    let form = document.createElement("form");
    concluirEl.type = "submit";
    form.action = "/GerenciarManutencaoVistoria";
    form.method = "post";
    concluirEl.value = "🗑️";
    let novoh2 = document.createElement("h2");
    let novop = document.createElement("p");
    let feedbackRetornadoEl = document.getElementById('feedbackRetornado'); 
    if (feedback === "nada"){
        let novoh1 = document.createElement("h1");
        novoh1.innerHTML = "Não há feedbacks para esta máquina";
        feedbackRetornadoEl.appendChild(novoh1);
    } else {
        novop.innerHTML = feedback.mensagem;
        if (feedback.tipo === "ERRO")
            novoh2.innerHTML = "Assunto: " + feedback.titulo + " (Erro) ";
        else 
            novoh2.innerHTML = "Assunto: " + feedback.titulo + " (Comentário) ";
        divEl.appendChild(novoh2);
        divEl.appendChild(novop);
        form.appendChild(concluirEl);
        form.appendChild(hiddenEl);
        divEl.appendChild(form);
        feedbackRetornadoEl.appendChild(divEl);
         
    }
    feedbackRetornadoEl.style.display = "grid";
    let blockerEl = document.getElementById('bloquearConteudo');
    blockerEl.style.display = "block";
}

function relatorioFeedback(event){
    let str = event.target.parentNode.children[1].children[0].innerHTML;
    if (str.match(/COD-(\d+)/) !== null){          
        let cod = str.match(/COD-(\d+)/)[1];
        let feedbackRetornadoEl = document.getElementById('feedbackRetornado');
        feedbackRetornadoEl.innerHTML = ""; 
        let naoEncontrado = true; 
        for (var i = 0; i < vetorFeedbackArray.length; i++) {
            let objetoAtual = vetorFeedbackArray[i];           
            if (objetoAtual.codigo == cod) {
                mostrarFormFeedback(objetoAtual);
                naoEncontrado = false;
            }
        }
        if (naoEncontrado === true)
            mostrarFormFeedback("nada");
        let botao = document.createElement("button");
        botao.classList.add("cancelarFeedback");
        botao.addEventListener('click', fecharFormFeedback);
        botao.innerHTML = "Cancelar";
        feedbackRetornadoEl.appendChild(botao);   
    }
}

function fecharFormFeedback(){
    let feedbackRetornadoEl = document.getElementById('feedbackRetornado');
    let blockerEl = document.getElementById('bloquearConteudo');
    blockerEl.style.display = "none";
    feedbackRetornadoEl.style.display = "none";
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

let botaoRelatorioEl = document.getElementById('botaoFeedback');
botaoRelatorioEl.addEventListener('click', relatorioFeedback);





let cancelarFeedbackEl = document.querySelector(".cancelarFeedback");
cancelarFeedbackEl.addEventListener('click', fecharFormFeedback);

let buscaEl = document.getElementById('busca');
buscaEl.addEventListener('input', pesquisarElementos);