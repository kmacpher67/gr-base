// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.1.3.js
//= require bootstrap-all.js
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
            $(this).fadeOut();
        });
    })(jQuery);
}

$(document).ready(function() {
    loadMasks();
});

function loadMasks() {
    $(".phone").mask("(00) 0000-0000");
    $(".postalCode").mask("00000-000");
    $('.money').maskMoney({thousands:'.', decimal:',', allowNegative: true});
    $(".number").mask("0#", {maxlength: false});
    $(".input-append.dateTime").datetimepicker({
        pickSeconds: false,
        language: "pt-BR",
        format: 'dd/MM/yyyy HH:mm'
    });
    $(".input-append.dateTime .form-control").mask('00/00/0000 00:00');
    $(".input-append.date").datetimepicker({
        pickTime: false,
        language: "pt-BR",
        format: 'dd/MM/yyyy'
    });
    $(".input-append.date .form-control").mask('00/00/0000');
}
