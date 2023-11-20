
function criarSlotMaquina(maquinasArray){
    
    maquinasArray.forEach(function(maquina) {

        let menuEl = document.getElementById("menu");
        let ListaMaquinasEl = document.getElementById("ListaMaquinas");
        let novoSlot = document.createElement("div");
        let novoh1 = document.createElement("h3");
        let novoh2 = document.createElement("h2");
        let novoh3 = document.createElement("h3");
        let novaImg = document.createElement("img");

        novoSlot.classList.add("slot"); 
        let novoId = 'id-' + contadorIds++;
        novoSlot.id = novoId;
        novoh1.id = "Locatario";
        novoh1.style.display = 'none';
        
        novoh1.innerHTML = maquina.locatario;
        novoh2.innerHTML = maquina.nome;
        novoh3.innerHTML = maquina.codigo;

        converterBytesEmImagem(maquina.img, novaImg);
        novaImg.alt = "Imagem da "+ maquina.nome; 

        menuEl.insertBefore(novoSlot,ListaMaquinasEl);
        novoSlot.appendChild(novaImg);
        novoSlot.appendChild(novoh2);
        novoSlot.appendChild(novoh3);
        novoSlot.onclick = function() {
            mostrarInformacoes(this);
         };
    }); 
}
let contadorIds = 1;
function mostrarInformacoes(elemento) {
    
  let imgSrc = elemento.querySelector('img').src;
  let h1Text = elemento.querySelector('h1').innerText;
  let h2Text = elemento.querySelector('h2').innerText;
  let h3Text = elemento.querySelector('h3').innerText;


  let informacoesDiv = document.getElementById('informacoes');
  informacoesDiv.innerHTML = `
    <img src="${imgSrc}" alt="Imagem">
    <h2 id="nomeMaquina">${h2Text}</h2>
    <p>COD-<h3 id="codeMaquina">${h3Text}</h3></p>
    <h1 id="Locatario" ><p>Aluda Por: </p>${h1Text}</h1>
  `;
}

function PesquisarPorCodigo() {
  let codigoDigitado = document.getElementById('Pesquisa').value;
  let maquinasArray = document.querySelectorAll('.slot');
  let elementosOcultos = [];

  maquinasArray.forEach(function(elemento) {
    let codigoNoElemento = elemento.querySelector('h3').innerText;
    if (codigoDigitado === codigoNoElemento) {
      console.log('Código encontrado!');
    } else {
      elemento.style.display = 'none';
      elementosOcultos.push(elemento);
    }
  });

  if (codigoDigitado === '') {
    if (elementosOcultos.length === maquinasArray.length) {
      maquinasArray.forEach(function(elemento) {
        elemento.style.display = 'flex';
      });
    } else {
      elementosOcultos.forEach(function(elemento) {
        elemento.style.display = 'flex';
      });
    }
  }
}

function converterBytesEmImagem(url, imagemEl) {
  if (url === 'none' || url === '') {
    imagemEl.src = 'img/NonePhoto.png';
  } else {
    imagemEl.src = url;
  }
}

let EnviarEl = document.getElementById('enviar');
EnviarEl.addEventListener('click', PesquisarPorCodigo);

function criarSlotFeedback(codigoMaquina,feedbackArray){
    contadorIds=1;
    feedbackArray.forEach(function(feedback) {

        let ListarFeedbaksEl = document.getElementById("ListarFeedbaks");
        let ListarEl = document.getElementById("Listar");
        let novoFeedback = document.createElement("div");
        let novoh1 = document.createElement("h1");
        let novoh2 = document.createElement("h2");
        let novoh3 = document.createElement("h3");
        let novop = document.createElement("p");

        novoFeedback.classList.add("Feedback"); 
        let novoId = 'id-' + contadorIds++;
        novoFeedback.id = novoId;
        
        novoh1.innerHTML = feedback.codigo ;
        novoh2.innerHTML = feedback.titulo;
        novoh3.innerHTML = feedback.Tipo;
        novop.innerHTML = feedback.mensagem;



        ListarFeedbaksEl.insertBefore(novoFeedback,ListarEl);
        novoFeedback.appendChild(novoh1);
        novoFeedback.appendChild(novoh2);
        novoFeedback.appendChild(novoh3);
        novoFeedback.appendChild(novop);
    }); 
}

let botaoCancelarEl = document.querySelector("#cancelar");
let ListarFeedbaksEl = document.getElementById("ListarFeedbaks");

function fecharFormularios(){
        ListarFeedbaksEl.style.display = "none"; 
}
botaoCancelarEl.addEventListener("click", fecharFormularios);

let emitirFeedbacksEL = document.getElementById("emitirFeedbacks");
emitirFeedbacksEL.addEventListener("click", function(){
    let ListarFeedbaksEl = document.getElementById('ListarFeedbaks');
    let informacoesDiv = document.getElementById('informacoes');
    let codigoNoElemento = informacoesDiv.querySelector('h3').innerText;
    criarSlotFeedback(codigoNoElemento,FeedbackArray);
    ListarFeedbaksEl.style.display = "block"; 
    
});
