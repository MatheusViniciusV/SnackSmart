let $contratos_mini = $('article.contratos');

$contratos_mini.click(function (e) {
    let selecionado = $(this).hasClass('selecionado');

    $contratos_mini.removeClass('selecionado');

    if(!selecionado)
       $(this).addClass('selecionado');
        
});