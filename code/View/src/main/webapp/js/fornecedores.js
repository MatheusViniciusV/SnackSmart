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
function mostrarForm(){
    let addContatoEl = document.getElementById('addContato');
    let blockerEl = document.getElementById('bloquearConteudo');
    addContatoEl.style.display = "grid";
    blockerEl.style.display = "block";
}
function fecharForm(){
    let addContatoEl = document.getElementById('addContato');
    let blockerEl = document.getElementById('bloquearConteudo');
    blockerEl.style.display = "none";
    addContatoEl.style.display = "none";
}
function slotSelecionado(){
    let texto = this.children[0].innerHTML;
    let substrings = texto.split('<br>');
    substrings = substrings.map(str => str.replace(/<[^>]+>/g, '').trim());
    for (var i = 0; i < vetorFornecedores.length; i++) {
        let objetoAtual = vetorFornecedores[i];           
        if (objetoAtual.nome == substrings[0]) {
            mostrarInfo(objetoAtual.nome, objetoAtual.telefone, objetoAtual.email, objetoAtual.id);
        }
    }   
}

function mostrarInfo(nome, telefone, email, id){
    let nomeExibidoEl = document.getElementById('nomeExibido');
    let telefoneExibidoEl = document.getElementById('telefoneExibido');
    let emailExibidoEl = document.getElementById('emailExibido');
    let idFornecedor = document.getElementById('idFornecedor');
    nomeReverta = nome;
    emailReverta = email;
    telefoneReverta = telefone;
    nomeExibidoEl.value = nome;
    telefoneExibidoEl.value = telefone;
    emailExibidoEl.value = email;
    idFornecedor.value = id;
}

function criarSlot(nome, telefone, email){  
    let resultadoEl = document.getElementById("resultFornecedor");
    let novoSlot = document.createElement("div");
    let novoh2 = document.createElement("h2");
    
    novoSlot.classList.add("slotClick");
    novoh2.innerHTML = nome + '<br>' + "TEL: " + telefone + '<br>' + "Email: " + email;
    
    resultadoEl.appendChild(novoSlot);
    novoSlot.appendChild(novoh2);
      
    novoSlot.addEventListener("click", slotSelecionado);
}

function reverte(){
    let nomeExibidoEl = document.getElementById('nomeExibido');
    let telefoneExibidoEl = document.getElementById('telefoneExibido');
    let emailExibidoEl = document.getElementById('emailExibido');
    if (nomeReverta !== undefined){
        nomeExibidoEl.value = nomeReverta;
        telefoneExibidoEl.value = telefoneReverta;
        emailExibidoEl.value = emailReverta; 
    }
}

let buscaEl = document.getElementById('busca');
buscaEl.addEventListener('input', pesquisarElementos);

let AddFonercedorEl = document.getElementById('AddFonercedor');
AddFonercedorEl.addEventListener('click', mostrarForm);

let cancelaEl = document.querySelectorAll(".cancelar");
cancelaEl.forEach(function(botao) {
    botao.addEventListener('click', fecharForm);
});

let locatarioEl = document.getElementById('locatario');
locatarioEl.value = usuario.cpf;

let reverterEl = document.getElementById('reverter');
reverterEl.addEventListener('click', reverte);
//Global VAR - Reverter
var nomeReverta;
var telefoneReverta;
var emailReverta;