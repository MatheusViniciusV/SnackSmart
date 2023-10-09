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
        $('#solicita-cancelar-contrato').addClass('null');

    }, 1000);

    var dataToSend = {
        minhaString: "cu" // Altere isso para a string que deseja enviar
    };

    $.ajax({
        type: 'POST', // Tipo de solicitação (POST)
        url: '/SolicitarCancelarContratos', // URL do servlet
        data: dataToSend, // Dados a serem enviados no corpo da solicitação
        success: function (response) {
            // Ação a ser executada em caso de sucesso
            alert("Solicitação bem-sucedida:" + response);

            // Você pode adicionar código aqui para lidar com a resposta do servidor
        },
        error: function (error) {
            // Ação a ser executada em caso de erro
            console.log("oi");
            console.error(error);

            // Você pode adicionar código aqui para lidar com o erro
        }
    });
})