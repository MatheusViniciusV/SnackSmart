let $contratosMini = $('article.contratos');
$contratosMini.click(function () {
    let selecionado = $(this).hasClass('selecionado');

    $contratosMini.removeClass('selecionado');

    if(!selecionado) {
        $(this).addClass('selecionado');
        $('#solicita-cancelar-contrato').removeClass('null');
    } else {
        $('#solicita-cancelar-contrato').addClass('null');
    }
        
});


let $solicitarCancelamentoBtn = $('#solicita-cancelar-contrato');
$solicitarCancelamentoBtn.click(function () {
    //TODO Enviar para o servlet.
    
    // Adicionar essa animação quando o servlet confirmar
    $(this).addClass('btn-confirmado');

    setTimeout(function() {
        $('#solicita-cancelar-contrato').removeClass('btn-confirmado');
        $contratosMini.removeClass('selecionado');
        $(this).addClass('null');

    }, 1000);
})