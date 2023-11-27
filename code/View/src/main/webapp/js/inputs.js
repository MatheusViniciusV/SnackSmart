/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const cpfRegEx = /\d{3}\.\d{3}\.\d{3}\-\d{2}/;
const telRegEx = /\(\d{2}\)\d{9}/;
const emailRegEx = /^[a-z0-9.]+@[a-z0-9]+\.[a-z]+(\.[a-z]+)?$/i;

function validarRegEx(input, regEx, validador) {
    let $input = $(input);

    if(!regEx.test($input.val())) {
        $input.addClass('errado');
        $input.val('');
        validador = false;
    }
}

let $cpfInputs = $(".cpf-input");
$cpfInputs.on('input', function () {
    if ($cpfInputs.val().length === 3 || $cpfInputs.val().length === 7) {
        $cpfInputs.val($cpfInputs.val() + '.');
    } else if ($cpfInputs.val().length === 11) {
        $cpfInputs.val($cpfInputs.val() + '-');
    }
});
