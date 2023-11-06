$('#criar-contrato').click(function () {
    $('#criar-contrato-form').removeClass('oculto')
});

$('#cancelar').click(function () {
    $('#criar-contrato-form').addClass('oculto');
    $('input').removeClass('errado');
    
});

const $inputCpfLocatario = $('input[name=locatario-cpf]');
$inputCpfLocatario.on('input', function () {
    if ($inputCpfLocatario.val().length === 3) {
        $.ajax({
            type: 'POST',
            url: 'obterLocatario',
            data: {
                locatarioCpf: $inputCpfLocatario.val()
            },
            success: function (response) {
                $('input[name=locatario-nome]').val(response.nome);
                $('input[name=locatario-telefone]').val(response.telefone);
                $('input[name=locatario-email]').val(response.email);
            }
        });
    }
});

const $cancelarContratoBtn = $('#cancelar-contrato');
if($contratoSelecionado !== null) {
    $cancelarContratoBtn.click(function () {
        $.ajax({
            url: 'cancelarContrato',
            type: 'POST',
            data: {
                id: $contratoSelecionado.data('id')
            },
            success: function () {
                $contratoSelecionado.addClass('btn-confirmado');

                setTimeout(function() {
                    $contratoSelecionado.remove();
                    $contratoSelecionado.removeClass('btn-confirmado');
                    $contratoSelecionado.addClass('null');
                    $('#pdf-contrato').addClass('null');
                    $('#pdf-contrato a').removeAttr('href');
                    $contratoSelecionado = null;
                }, 750);

                console.log(`Contrato deletado com sucesso.`);
            }
        })
    })
}

$('#enviar').click(function() {

    let formCorreto = true;
    
    validarRegEx('input[name=locatario-cpf] .obrigatorio', cpfRegEx, formCorreto);
    validarRegEx('input[name=locatario-email] .obrigatorio', emailRegEx, formCorreto);
    validarRegEx('input[name=locatario-telefone] .obrigatorio', telRegEx, formCorreto);

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
            cpfLocatario: $('input[name=locatario-cpf]').val(),
            // dados do contrato
            dataInicio: $('input[name=data-inicio]').val(),
            dataFim: $('input[name=data-termino]').val(),
            diaPagamento: $('input[name=dia-pagamento]').val(),
            observacoes: $('textarea[name=observacoes]').val(),
            valor: $('input[name=valor]').val()
        };
    
        $.ajax({
            type: 'POST', 
            url: 'CriarContrato', 
            data: dataContrato, 
            success: function (response) {
                console.log(response);
                console.log(response.contrato);

                let $contrato = $('<article>');
                $contrato.append(`<h3>Contrato ${response.contrato.id}</h3>`);
                $contrato.append(`<div>Locatario: ${response.contrato.locatario}</div>`);
                $contrato.append(`<div>Data inicio: ${response.contrato.dataInicio}</div>`);
                $contrato.append(`<div>Data fim: ${response.contrato.dataFim}</div>`);
                $contrato.append(`<div>Dia do pagamento: ${response.contrato.diaPagamento}</div>`);
                $contrato.append(`<div>Valor: R$ ${response.contrato.valor}</div>`);
                $contrato.append(` <div>Status: <span class="vigente">vigente</span></div>`);

                $contrato.attr("id", `contrato-${response.contrato.id}`);
                $contrato.data('id', response.contrato.id);
                $contrato.addClass('contratos');

                $('#lista-contratos').append($contrato);

                $('#dados-locatario input').val('');
                $('#dados-contrato input').val('');
            },
            error: function (error) {
                
                console.error(error);
    
            }
        });
    }

});