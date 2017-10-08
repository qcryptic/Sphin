/**
 * Created by Kyle on 9/30/2017.
 */
var settingsTimeout;
function settingsMouseOver() {
    if (settingsTimeout != null)
        clearTimeout(settingsTimeout);
    else {
        $('.settings-dropdown').dropdown('toggle');
        $('#navbarDropdownMenuLink').addClass('nav-link-bg');
    }
}

function settingsMouseOut() {
    settingsTimeout = setTimeout(function(){
        $('.settings-dropdown').dropdown('toggle');
        $('#navbarDropdownMenuLink').removeClass('nav-link-bg');
        settingsTimeout = null;
    }, 600);
}

function settingsClick() {
    window.location.href = 'settings?type=about';
}

$('#settings-button').on('hide.bs.dropdown', function () {
    clearTimeout(settingsTimeout);
    settingsTimeout = null;
});