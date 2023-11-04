$('#criar-contrato').click(function () {
    $('#criar-contrato-form').removeClass('oculto')
});

$('#cancelar').click(function () {
    $('#criar-contrato-form').addClass('oculto');
    $('input').removeClass('errado');
    
});

$('#enviar').click(function() {

    let formCorreto = true; 
    
    validarRegEx('input[name=locatario-cpf]', cpfRegEx, formCorreto);
    validarRegEx('input[name=locatario-email]', emailRegEx, formCorreto);
    validarRegEx('input[name=locatario-telefone]', telRegEx, formCorreto);

    $('input').each(function() {
        if($(this).val() === '') {
            $(this).addClass('errado');
            formCorreto = false;
        } else {
            $(this).removeClass('errado');
        }
    });



    if(formCorreto) {


        $('#criar-contrato-form').addClass('oculto');
    
        const dataContrato = {
            // dados do locatario
            nomeLocatario: $('input[name=locatario-nome]').val(),
            cpfLocatario: $('input[name=locatario-cpf]').val(),
            emailLocatario: $("input[name=locatario-email]").val(),
            telefoneLocatario: $('input[name=locatario-telefone]').val(),
            // dados do contrato
            dataInicio: $('input[name=data-inicio]').val(),
            dataFim: $('input[name=data-termino]').val(),
            dataPagamento: $('input[name=data-pagamento]').val(),
            Observacoes: $('textarea[name=observacoes]').val(),
        };
    
        $.ajax({
            type: 'POST', 
            url: 'CriarContrato', 
            data: dataContrato, 
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
    
                console.log(`Contrato ${dataContrato.id} deletado com sucesso.`);
            },
            error: function (error) {
                
                console.error(error);
    
            }
        });
    }

});