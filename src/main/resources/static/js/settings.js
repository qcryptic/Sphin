/**
 * Created by Kyle on 10/8/2017.
 */

/* GENERAL */
var alertTimeout;
function settingsAlertSuccess(msg, time) {
    $('#settings-alert')
        .html('<i class="fa fa-check"></i> '+msg)
        .removeClass('alert-warning').removeClass('alert-danger').addClass('alert-success')
        .slideDown();
    setAlertTimeout(time);
}
function settingsAlertWarning(msg, time) {
    $('#settings-alert')
        .html('<i class="fa fa-exclamation-triangle"></i> '+msg)
        .removeClass('alert-success').removeClass('alert-danger').addClass('alert-warning')
        .slideDown();
    setAlertTimeout(time);
}
function settingsAlertDanger(msg, time) {
    $('#settings-alert')
        .html('<i class="fa fa-times"></i> '+msg)
        .removeClass('alert-warning').removeClass('alert-success').addClass('alert-danger')
        .slideDown();
    setAlertTimeout(time);
}
function setAlertTimeout(time) {
    if (time === undefined)
        time = 5000;
    clearTimeout(alertTimeout);
    alertTimeout = setTimeout(function () {
        $('#settings-alert').slideUp();
    },time);
}
function changeAlertText(msg) {
    $('#settings-alert').html(msg);
}
function goToSetting(type, item) {
    $('body').addClass('load');
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
function restartApp() {
    var url = window.location.href.substring(0,window.location.href.lastIndexOf("/"));
    postRequest(url+"/restart", {}, function () {}, function (xhr) {settingsAlertDanger('Error restarting sphin: '+xhr.status+' error');});
    settingsAlertWarning('Restarting Sphin in 5 seconds', 10000);
    var timeleft = 5;
    var downloadTimer = setInterval(function(){
        timeleft--;
        changeAlertText('<i class="fa fa-exclamation-triangle"></i> Restarting Sphin in '+timeleft+' seconds');
        if(timeleft <= 0) {
            clearInterval(downloadTimer);
            location.reload();
        }
    },1000);
}

/* NETWORK */
function updateNetwork() {
    postRequest(window.location.href+"/update", {port:$('#network-port')[0].value, ip:$('#network-ip')[0].value, url:$('#network-base')[0].value},
        function (response) {
            if (response.result) {
                doSuccessButton('network-save', true, 'Saved');
                showAlert('network-alert', 'success', response.message, true);
            }
            else {
                doErrorButton('network-save', true, 'Error Saving');
                showAlert('network-alert', 'danger', response.message, true);
            }
        },
        function (xhr) {
            doErrorButton('network-save', true, 'Error Saving');
            showAlert('network-alert', 'danger', 'Error updating - '+xhr.status+' error', true);
        }
    );
}

/* USERS AND INVITES */
function updateGroupCaret(id) {
    var selector = $('#'+id);
    if (selector.hasClass('fa-caret-right'))
        selector.removeClass('fa-caret-right').addClass('fa-caret-down');
    else
        selector.removeClass('fa-caret-down').addClass('fa-caret-right');
}
function addGroup() {
    var k =
    '<div class="card-header users-card-header" role="tab" id="headingOne">'+
        '<h5 class="mb-0 pull-left">'+
            '<a class="users-card-title" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">'+
                '<i class="fa fa-caret-right"></i> Admin'+
            '</a>'+
        '</h5>'+
        '<button class="btn type-button small-type-button pull-right" onclick="makeInvite()"><i class="fa fa-plus"></i> Invite Link</button>'+
        '<div class="clearfix"></div>'+
    '</div>'+
    '<div id="collapseOne" class="collapse" role="tabpanel" aria-labelledby="headingOne">'+
        '<div class="card-block">'+
            'Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably havent heard of them accusamus labore sustainable VHS.'+
        '</div>'+
    '</div>';
    $('#group-placeholder').append(k);
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

/* Connections */
function showMediaManager(type, selection, collapseId) {
    (type === 'movie') ? $('#dropdownMenuMovieManager').html(selection) : $('#dropdownMenuTvManager').html(selection);
    $('#'+collapseId).collapse('show');
}
function resetConnectionItems(id) {
    doNormalButton(id+'-save', 'Save');
    doNormalButton(id+'-test', 'Test Connection');
    hideAlert(id+'-alert');
}
function updateDarr(type, name) {
    resetConnectionItems(type+'-man');
    var btn = type+'-man-save';
    postRequest(
        window.location.href+"/update-"+name,
        {url:$('#'+name+'-url').val(),api:$('#'+name+'-api').val(),pathId:$('#'+name+'-path').val(),profileId:$('#'+name+'-profile').val()},
        function (response) {
            if (response.result)
                doSuccessButton(btn, false, 'Saved');
            else {
                doErrorButton(btn, true, 'Save Failed');
                showAlert(type+'-man-alert', 'danger', response.message, true);
            }
        },
        function (xhr) {
            console.log(xhr);
            doErrorButton(btn, true, 'Save Failed');
        }
    )
}
function testDarr(type, name) {
    resetConnectionItems(type+'-man');
    var btn = type+'-man-test';
    var curProfile = parseInt($('#'+name+'-profile').val(), 10);
    var curPath = parseInt($('#'+name+'-path').val(), 10);
    doLoadButton(btn);
    postRequest(window.location.href+"/testDarr", {url:$('#'+name+'-url').val(),api:$('#'+name+'-api').val()},
        function (response) {
            if (response.result) {
                $('#'+name+'-profile').html('');
                $('#'+name+'-path').html('');
                doSuccessButton(btn, true, 'Connected!');
                var json = JSON.parse(response.message);
                json.profiles.forEach(function(obj) { appendOption(name+'-profile', obj.id, obj.name, false) });
                json.paths.forEach(function(obj) { appendOption(name+'-path', obj.id, obj.path, false) });
                if (curProfile > 0)
                    $('#'+name+'-profile').val(curProfile);
                if (curPath > 0)
                    $('#'+name+'-path').val(curPath);
            }
            else {
                doErrorButton(btn, true, 'Connection Failed');
                showAlert(type+'-man-alert', 'danger', response.message, true);
            }
        },
        function (xhr) {
            console.log(xhr);
            doErrorButton(btn, true, 'Connection Failed');
            showAlert(type+'-man-alert', 'danger', 'Http error code: ' + xhr.status, true);
        }
    );
}