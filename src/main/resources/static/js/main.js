function postRequest(url, data, success, fail) {
    $.ajax({
        url: url,
        type: 'POST',
        data: data,
        timeout: 15000,
        success: success,
        error: fail
    });
}

function parameterTest(bool) {
    return (typeof bool !== 'undefined');
}

function doLoadButton(id) {
    $('#'+id).html('<i class="fa fa-spinner fa-pulse fa-fw"></i>');
}
function doNormalButton(id, text) {
    var selector = $('#'+id);
    selector.html(text);
    selector.removeClass('green-button').removeClass('red-button');
}
function doSuccessButton(id, addCheck, text) {
    var selector = $('#'+id);
    selector.addClass('green-button').removeClass('red-button');
    var msg = 'Success';
    if (parameterTest(text))
        msg = text;
    if (addCheck)
        msg = '<i class="fa fa-check"></i> ' + msg;
    selector.html(msg);
}
function doErrorButton(id, addCheck, text) {
    var selector = $('#'+id);
    selector.addClass('red-button').removeClass('green-button');
    var msg = 'Failed';
    if (parameterTest(text))
        msg = text;
    if (addCheck)
        msg = '<i class="fa fa-cross"></i> ' + msg;
    selector.html(msg);
}
function resetBtnAndAlert(alertId, buttonId, buttonText) {
    doNormalButton(alertId, buttonText);
    hideAlert(buttonId);
}
function resetSaveAndAlert(id, buttonText) {
    doNormalButton(id+'-save', buttonText);
    hideAlert(id+"-alert");
}
function showAlert(id, type, message, showX) {
    if (showX)
        message = '<button type="button" class="close" onclick="hideAlert(\''+id+'\')"><span aria-hidden="true">&times;</span></button>' + message;
    if (type === 'success')
        $('#'+id).html(message).removeClass('alert-warning').removeClass('alert-danger').addClass('alert-success').fadeIn();
    else if (type === 'danger')
        $('#'+id).html(message).removeClass('alert-warning').removeClass('alert-success').addClass('alert-danger').fadeIn();
    else if (type === 'warning')
        $('#'+id).html(message).removeClass('alert-success').removeClass('alert-danger').addClass('alert-warning').fadeIn();
}
function hideAlert(id) {
    $('#'+id).fadeOut();
}

function appendOption(id, value, text, isSelected) {
    if (isSelected)
        $('#' + id).append('<option value="' + value + '">' + text + '</option>');
    else
        $('#' + id).append('<option value="' + value + '">' + text + '</option>');
}