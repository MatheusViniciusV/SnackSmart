let $contratoSelecionado;
let $contratosMini = $('article.contratos');
function disponibilizarSelecaoDeContratos() {
    $contratosMini = $('article.contratos');
    $contratosMini.click(function () {
        let selecionado = $(this).hasClass('selecionado');

        $contratosMini.removeClass('selecionado');

        if(!selecionado) {
            $(this).addClass('selecionado');
            $('#solicita-cancelar-contrato').removeClass('null');
            $('#cancelar-contrato').removeClass('null');
            $('#pdf-contrato').removeClass('null');
            $contratoSelecionado = $(this);
            const urlDonwloadPDF = 'PDFContrato?id=' + $contratoSelecionado.data('id') + '&locatarioCPF=' + $contratoSelecionado.data('cpf');
            $('#pdf-contrato a').attr('href', urlDonwloadPDF);
        } else {
            $('#solicita-cancelar-contrato').addClass('null');
            $('#cancelar-contrato').addClass('null');
            $('#pdf-contrato').addClass('null');
            $('#pdf-contrato a').removeAttr('href');
            $contratoSelecionado = null;
        }

    });
}

disponibilizarSelecaoDeContratos();

let $solicitarCancelamentoBtn = $('#solicita-cancelar-contrato');

if($contratoSelecionado !== null) {

    $solicitarCancelamentoBtn.click(function () {
        const contratoData = {
            locatarioCPF: $contratoSelecionado.data('cpf'),
            id: $contratoSelecionado.data('id'),
        };
    
        $.ajax({
            type: 'POST', 
            url: $('#solicita-cancelar-contrato').data("calcelar"), 
            data: contratoData,
            success: function (response) {
    
                $('#solicita-cancelar-contrato').addClass('btn-confirmado');
                
                setTimeout(function() {
                    $contratoSelecionado.remove();
                    $solicitarCancelamentoBtn.removeClass('btn-confirmado');
                    $solicitarCancelamentoBtn.addClass('null');
                    $('#pdf-contrato').addClass('null');
                    $('#pdf-contrato a').removeAttr('href');
                    $contratoSelecionado = null;
                }, 750);
                
                console.log(`Contrato ${contratoData.id} deletado com sucesso.`);
            },
            error: function (error) {
                
                console.error(error);
    
            }
        });
    });
}
