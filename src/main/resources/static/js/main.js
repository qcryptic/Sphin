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