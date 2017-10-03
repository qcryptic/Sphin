function postReqest(url, data, success, fail) {
    $.ajax({
        url: url,
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        data: data,
        timeout: 15000,
        success: success,
        error: fail
    });
}