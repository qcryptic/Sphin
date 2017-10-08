/**
 * Created by Kyle on 10/7/2017.
 */
function register() {
    alert('register');
}

function login() {
    var url = window.location.href;
    url = url.substring(0,url.lastIndexOf("/"));
    postRequest(
        url + "/login",
        {user:$('#username')[0].value,pass:$('#password')[0].value},
        function (response) {
            if (response.result)
                window.location.replace(url + "/search");
            else
                $('#login-text').html(response.message);
        },
        function (xhr) {
            $('#login-text').html("Error logging in: " + xhr.status + " error");
        }
    );
}

function registerAdmin() {
    postRequest(
        window.location.href+"/addAdmin",
        {user:$('#username')[0].value,pass:$('#password')[0].value},
        function(response) {
            if (response.result)
                login();
            else
                $('#login-text').html(response.message);
        },
        function(xhr) {
            $('#login-text').html("Error creating account: " + xhr.status + " error");
        }
    );
}

function doLogin() {
    clearLoginMessage();
    var errors = validateInputs();
    if (errors) {
        $('#login-text').html(errors);
        return;
    }
    if (loginType === 'register')
        register();
    else if (loginType === 'login')
        login();
    else if (loginType === 'admin')
        registerAdmin();
}

function validateInputs() {
    var errors = '';
    var user = $('#username')[0].value;
    var pass = $('#password')[0].value;
    var alphanumericRegex = /^\w+$/;
    if (user.length < 1)
        return 'Username is required';
    if (pass.length < 1)
        return 'Password is required';
    if (user.length > 30)
        return 'Username must be 30 characters or less';
    if (pass.length > 50)
        return 'Password must be 50 characters or less';
    if (!alphanumericRegex.test(user))
        return 'Username must be letters or numbers only';
    if (!alphanumericRegex.test(pass))
        return 'Password must be letters or numbers only';
    return errors;
}

function clearLoginMessage() {
    $('#login-text').html('');
}

$('#username').keyup(function(e){
    if(e.keyCode === 13)
        doLogin();
});

$('#password').keyup(function(e){
    if(e.keyCode === 13)
        doLogin();
});