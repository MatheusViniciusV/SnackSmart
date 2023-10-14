let dataAtual = new Date();
anoAtual = dataAtual.getFullYear();
mesAtual = dataAtual.getMonth();

let mesAnterior = document.querySelector("#mesAnterior");
let proximoMes = document.querySelector("#proximoMes");
let Mes = document.querySelector("#Mes");
let Ano = document.querySelector("#Ano");

let Meses = ["Janeiro ", "Fevereiro ", "Março ", "Abril ", "Maio ", "Junho ", "Julho ", "Agosto ", "Setembro ", "Outubro ", "Novembro ", "Dezembro "];
proximoMes.addEventListener("click", function () {

    for (var i = 0; i < Meses.length; i++) {
        if (Meses[i] == Mes.innerHTML)
            if (i != 11) {
                Mes.innerHTML = Meses[i + 1];
                break;
            } else {
                Mes.innerHTML = Meses[0];
                i = -1;
                Ano.innerHTML = parseInt(Ano.innerHTML, 10) + 1;
                break;
            }

    }
    setDias(i + 1);
});
mesAnterior.addEventListener("click", function () {

    for (var i = 0; i < Meses.length; i++) {
        if (Meses[i] == Mes.innerHTML)
            if (i != 0) {
                Mes.innerHTML = Meses[i - 1];
                break;
            } else {
                Mes.innerHTML = Meses[11];
                i = 12;
                Ano.innerHTML = parseInt(Ano.innerHTML, 10) - 1;
                break;
            }
    }
    setDias(i - 1);

});

function setData() {

    Ano.innerText = `${anoAtual}`;
    Mes.innerText = Meses[`${mesAtual}`];
    setDias(`${mesAtual}`);
}
setData();
function setDias(nMes) {
    let primeiroDiaSemana = new Date(Ano.innerHTML, nMes, 1).getDay();
    let ultimoDia = new Date(Ano.innerHTML, nMes + 1, 0).getDate();
    let dias = document.querySelectorAll("#dias li");
    let rep = 0;
    dias.forEach(element => {
        if (rep < primeiroDiaSemana) {
            element.style.visibility = "hidden";
        } else if (rep > (ultimoDia + primeiroDiaSemana - 1)) {
            element.style.visibility = "hidden";
        } else
            element.style.visibility = "visible";

        rep++;

    });
    rep = 0;
    dias.forEach(element => {
        if (element.style.visibility != "hidden") {
            rep++;
            element.innerHTML = rep;
        }

    });
}
