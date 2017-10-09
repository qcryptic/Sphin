/**
 * Created by Kyle on 10/8/2017.
 */
function goToSetting(type) {
    window.location.href = type;
}

function setSaveBtnMsg(msg, type) {
    var item = $('#save-btn-msg');
    if (type === 'success') {
        item.removeClass('text-danger').addClass('text-success');
        item.html('<i class="fa fa-check"></i> '+msg);
    }
    else {
        item.removeClass('text-success').addClass('text-danger');
        item.html('<i class="fa fa-times"></i> '+msg);
    }
}

function clearSaveBtnMsg() {
    $('#save-btn-msg').html('');
}

function makeInvite() {
    postRequest(window.location.href+"/makeInvite", {rank:'mod'},
        function (response) {
            if (response.result)
                alert(response.message);
        }, function (xhr) {

        }
    );
}

function updateNetwork() {
    postRequest(window.location.href+"/update", {port:$('#network-port')[0].value, ip:$('#network-ip')[0].value, url:$('#network-base')[0].value},
        function (response) {
            if (response.result)
                setSaveBtnMsg(response.message, 'success');
            else
                setSaveBtnMsg(response.message, 'danger');
        },
        function (xhr) {
            setSaveBtnMsg('Error updating - '+xhr.status+' error', 'danger');
        }
    );
}