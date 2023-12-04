let bloquearConteudoEl = document.getElementById("bloquearConteudo");
let formularioAdd = document.getElementById("formLote");
let formularioUpdate = document.getElementById("formAtualizarLote");
let botoesCancelarEl = document.querySelectorAll(".cancelar");

function criarSlotLotes(nome, quantidade, url, id){  
    let resultadoEl = document.getElementById("resultLote");
    let novoSlot = document.createElement("div");
    let novoh2 = document.createElement("h2");
    let novaImg = document.createElement("img");
    
    novoSlot.classList.add("slotClick");
    novoh2.innerHTML = "COD:" + id + '<br>' + nome + '<br>' + "× " + quantidade;

    converterBytesEmImagem(url, novaImg);
    novaImg.alt = "Imagem da "+ nome; 

    resultadoEl.appendChild(novoSlot);
    novoSlot.appendChild(novaImg);
    novoSlot.appendChild(novoh2);
      
    
    novoSlot.addEventListener("click", slotSelecionado);
}

function mostrarInfo(nome, quantidade, precoVenda, precoCompra, imagem, fornecedor, id){
    precoVenda = parseFloat(precoVenda); 
    precoVenda = precoVenda.toFixed(2); 
    precoCompra = parseFloat(precoCompra); 
    precoCompra = precoCompra.toFixed(2); 
    let imagemProdutoEl = document.getElementById('imagemProduto');
    document.getElementById('nomeProduto').innerHTML =  nome + " (COD: " + id + ")" + " ×" + quantidade;
    document.getElementById('fornecedorProduto').innerHTML = "Fornecido  por: " + fornecedor;
    document.getElementById('compraProduto').innerHTML = "Preço da compra: $" + precoCompra;
    document.getElementById('vendaProduto').innerHTML = "Preço de venda: $" + precoVenda;
    let loteIdEl = document.querySelectorAll('.loteId');
    loteIdEl[0].value = id;
    loteIdEl[1].value = id;
    document.getElementById("removerLote").style.display = "block";
    document.getElementById("atualizarLote").style.display = "block";
    imagemProdutoEl.src = imagem;
}

function slotSelecionado(){
    let texto = this.children[1].innerHTML;
    let cod = texto.match(/\d+/); 
    let url = this.children[0].src;
    cod = parseInt(cod[0]);
    for (var i = 0; i < vetorLotes.length; i++) {
        let objetoAtual = vetorLotes[i];   
        if (objetoAtual.id == cod) {
            mostrarInfo(objetoAtual.tipoProduto, objetoAtual.quantidade, objetoAtual.precoVenda, objetoAtual.precoCompra, url, objetoAtual.fornecedor, objetoAtual.id);
        }
    }
    
}

function converterBytesEmImagem(url, imagemEl){
    if (url === "none" || url === ""){
        imagemEl.src = "img/noneProduto.png"; 
    } else{
        imagemEl.src = "data:image/*;base64," + url;
    }  
}

var vetorNomes = [];
var vetorId = [];

function selectDinamicoFornecedor(){
    let fornecedorEl = document.getElementById("fornecedor");
    let NovofornecedorEl = document.getElementById("Novo-fornecedor");
    NovofornecedorEl.innerHTML = "";
    fornecedorEl.innerHTML = "";
    for (let i = 0; i < vetorNomes.length; i++){
        let option = document.createElement('option');
        option.value = vetorId[i];
        option.textContent = vetorNomes[i];
        fornecedorEl.appendChild(option);  
        let option2 = document.createElement('option');
        option2.value = vetorId[i];
        option2.textContent = vetorNomes[i];
        NovofornecedorEl.appendChild(option2); 
    }
}
function encontrarIdFornecedorPorNome(nomeProcurado) {
    for (let i = 0; i < vetorFornecedores.length; i++) {
        if (vetorFornecedores[i].nome === nomeProcurado) {
            return vetorFornecedores[i].id; 
        }
    }
}

function recuperarInfo(idProcurado){
    let objeto;
    for (let i = 0; i < vetorLotes.length; i++) {
        if (vetorLotes[i].id === idProcurado) {
            objeto = vetorLotes[i]; 
        }
    }
    document.getElementById("Novo-tipoProduto").value = objeto.tipoProduto;
    document.getElementById("Novo-quantidade").value = objeto.quantidade;
    document.getElementById("Novo-precoCompra").value = objeto.precoCompra;
    document.getElementById("Novo-precoVenda").value = objeto.precoVenda;
    document.getElementById("Novo-fornecedor").value = encontrarIdFornecedorPorNome(objeto.fornecedor);
}
function mostrarFormulario(formulario){
    selectDinamicoFornecedor();     
    try {
        recuperarInfo(document.querySelectorAll(".loteId")[0].value);
    } catch{}
    
    formulario.style.display = "grid"; 
    bloquearConteudoEl.style.display = "block";
}

function fecharFormularios(){
    formularioAdd.style.display = "none"; 
    formularioUpdate.style.display = "none"; 
    bloquearConteudoEl.style.display = "none";
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

let inputEl = document.getElementById('tipoProduto');

inputEl.addEventListener('input', function(event) {
  let inputValue = event.target.value;
  let regex = /^[a-zA-Z0-9\s]*$/; 
  
  if (!regex.test(inputValue)) {
    event.target.value = inputValue.slice(0, -1); 
  }
});

let buscaEl = document.getElementById('busca');
buscaEl.addEventListener('input', pesquisarElementos);

let addLoteEl = document.getElementById('addLote');
addLoteEl.addEventListener('click', function() {
    mostrarFormulario(formularioAdd);
});

let atualizarLoteEl = document.getElementById('atualizarLote');
atualizarLoteEl.addEventListener('click', function() {
    mostrarFormulario(formularioUpdate);
});

botoesCancelarEl.forEach(function(botao) {
    botao.addEventListener("click", fecharFormularios);
});



let inputsDinheiro = document.querySelectorAll('.dinheiro');

inputsDinheiro.forEach(input => {
    input.addEventListener('input', function() {
        this.value = this.value.replace(/[^0-9.]/g, '');
        let pontp = this.value.match(/\./g);
        if (pontp && pontp.length > 1) {
            let primeiraParte = this.value.indexOf('.');
            this.value = this.value.substring(0, primeiraParte + 1) + this.value.substring(primeiraParte + 1).replace(/\./g, '');
        }
        let decimal = this.value.indexOf('.');
        if (decimal !== -1 && this.value.substring(decimal + 1).length > 2) {
            let depoisDecimal = this.value.substring(decimal + 1, decimal + 3);
            this.value = this.value.substring(0, decimal + 1) + depoisDecimal;
        }
    });
});

