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
            type: 'POST', // Tipo de solicitação (POST)
            url: 'SolicitarCancelarContrato', // URL do servlet
            data: contratoDataSend, // Dados a serem enviados no corpo da solicitação
            success: function (response) {
                // Ação a ser executada em caso de sucesso
                // alert("Solicitação bem-sucedida:" + response);
    
                $('#solicita-cancelar-contrato').addClass('btn-confirmado');
    
                setTimeout(function() {
                    $contratoSelecionado.remove();
                    $('#solicita-cancelar-contrato').removeClass('btn-confirmado');
                    $('#solicita-cancelar-contrato').addClass('null');
                    $('#pdf-contrato').addClass('null');
                    $('#pdf-contrato a').removeAttr('href');
            
                }, 750);
    
                console.log(`Contrato ${contratoDataSend.id} deletado com sucesso.`);
            },
            error: function (error) {
                
                console.error(error);
    
            }
        });
    });
}

$('#criar-contrato').click(function () {
    $('#criar-contrato-form').removeClass('oculto')
});

$('#cancelar').click(function () {
    $('#criar-contrato-form').addClass('oculto')
});

$('#enviar').click(function() {
    alert('ainda não implementado.');
    $('#criar-contrato-form').addClass('oculto');

    const dataContrato = {
        // dados do locatario
        nomeLocatario: $('input[name=locatario-nome').val(),
        cpfLocatario: null,
        emailLocatario: null,
        telefoneLocatario: null,
        // dados do contrato
        dataInicio: null,
        dataTermino: null,
        dataPagamento: null,
        Observacoes: null,
    }

    alert(dataContrato.nomeLocatario);
});