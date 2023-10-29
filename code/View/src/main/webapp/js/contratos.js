let $contratoSelecionado;
let $contratosMini = $('article.contratos');
$contratosMini.click(function () {
    let selecionado = $(this).hasClass('selecionado');

    $contratosMini.removeClass('selecionado');

    if(!selecionado) {
        $(this).addClass('selecionado');
        $('#solicita-cancelar-contrato').removeClass('null');
        $('#pdf-contrato').removeClass('null');
        $('#pdf-contrato a').attr('href', 'PDFContrato?contrato=');
        $contratoSelecionado = $(this);
    } else {
        $('#solicita-cancelar-contrato').addClass('null');
        $('#pdf-contrato').addClass('null');
        $('#pdf-contrato a').removeAttr('href');
        $contratoSelecionado = null;
    }
        
});


let $solicitarCancelamentoBtn = $('#solicita-cancelar-contrato');

if($contratoSelecionado !== null) {

    $solicitarCancelamentoBtn.click(function () {
        //TODO Enviar para o servlet.
        
        // Adicionar essa animação quando o servlet confirmar
        $(this).addClass('btn-confirmado');
    
        setTimeout(function() {
            $('#solicita-cancelar-contrato').removeClass('btn-confirmado');
            $contratosMini.removeClass('selecionado');
            $('#solicita-cancelar-contrato').addClass('null');
    
        }, 1000);
    
        const contratoDataSend = {
            titulo: "oi",
            id: $contratoSelecionado.data('id'),
        };
    
        $.ajax({
            type: 'POST', 
            url: $('#solicita-cancelar-contrato').data("calcelar"), 
            data: contratoDataSend,
            success: function (response) {
    
                $('#solicita-cancelar-contrato').addClass('btn-confirmado');
                
                setTimeout(function() {
                    $contratoSelecionado.remove();
                    $('#solicita-cancelar-contrato').removeClass('btn-confirmado');
                    $('#solicita-cancelar-contrato').addClass('null');
                    $('#pdf-contrato').addClass('null');
                    $('#pdf-contrato a').removeAttr('href');
                    $contratoSelecionado = null;
                }, 750);
                
                console.log(`Contrato ${contratoDataSend.id} deletado com sucesso.`);
            },
            error: function (error) {
                
                console.error(error);
    
            }
        });
    });
}
