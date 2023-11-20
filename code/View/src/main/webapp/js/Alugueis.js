var maquinas = document.querySelector("#listaOculta");
let lista = document.querySelector("#listaMaquinas");
maquinas.forEach(function(maq){
    
    
    Paragrafo = document.createElement('p');
    Paragrafo.textContent = maq;
    lista.appendChild(Paragrafo);
});
