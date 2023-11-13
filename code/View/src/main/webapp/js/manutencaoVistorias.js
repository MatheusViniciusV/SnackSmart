
function criarSlotMaquina(maquinasArray){
    
    maquinasArray.forEach(function(maquina) {

        let menuEl = document.getElementById("menu");
        let ListaMaquinasEl = document.getElementById("ListaMaquinas");
        let novoSlot = document.createElement("div");
        let novoh2 = document.createElement("h2");
        let novoh3 = document.createElement("h3");
        let novaImg = document.createElement("img");

        novoSlot.classList.add("slot"); 
        let novoId = 'id-' + contadorIds++;
        novoSlot.id = novoId;

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
  let h2Text = elemento.querySelector('h2').innerText;
  let h3Text = elemento.querySelector('h3').innerText;


  let informacoesDiv = document.getElementById('informacoes');
  informacoesDiv.innerHTML = `
    <img src="${imgSrc}" alt="Imagem">
    <h2>${h2Text}</h2>
    <h3>${h3Text}</h3>
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
        elemento.style.display = 'block';
      });
    } else {
      elementosOcultos.forEach(function(elemento) {
        elemento.style.display = 'block';
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
