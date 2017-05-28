jQuery(document).ready(function () {
    createCurrencyTable();
});

jQuery(document).ajaxComplete(function () {
    $('#currency_selector').change(function(){
        selectorChanger()
    });
    $('#slot_start_selector').change(function(){
        selectorChanger()
    });
    $('#slot_end_selector').change(function(){
        selectorChanger()
    });
});

var selectorChanger = function () {
    ajaxParams(
        "filter",
        $('#currency_selector option:selected').text(),
        $('#slot_start_selector option:selected').text(),
        $('#slot_end_selector option:selected').text()
    );
};

var createCurrencyTable = function () {
    ajax("all")
};

var ajax = function (uri) {
    $.ajax({
        type: 'GET',
        url: document.URL + uri,
        success: function (result) {
            $('#currencyTable').html(result);
        }
    });
};

var ajaxParams = function (uri, currency, start, end) {
    $.ajax({
        type: 'GET',
        url: document.URL + uri + "/" + currency + "/" + start + "/" + end,
        success: function (result) {
            $('#currencyTable').html(result);
        }
    });
};