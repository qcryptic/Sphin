/**
 * Created by Kyle on 10/1/2017.
 */
function mediaSelected(id) {
    var otherId = null;
    if (id == "movie-btn") {
        otherId = 'tv-btn';
        $('#search-label').text("Movie Search");
    }
    else {
        otherId = 'movie-btn';
        $('#search-label').text("TV Show Search");
    }
    $('#'+id).addClass('type-button-selected').removeClass('type-button');
    $('#'+otherId).removeClass('type-button-selected').addClass('type-button');
    hideResultsContainer();
    $("#search-input").val('');
    $("#search-input").focus();
}

function isMovieSelected() {
    return $('#movie-btn').hasClass('type-button-selected');
}

$('#search-input').keyup(function(e){
    if(e.keyCode === 13) {
        getSearchResults();
    }
});

function showSearchMessage(message) {
    $('#search-message-placeholder').html(message);
}

function hideResultsContainer() {
    $('.results-container').hide()
}

function showResultsContainer() {
    $('.results-container').show()
}

function getSearchResults() {
    doLoadButton('main-search-btn');
    hideResultsContainer();
    $('#search-results-placeholder').html('');
    $('#search-results-placeholder-mobile').html('');
    $('#search-message-placeholder').html('');
    var query = $('#search-input')[0].value;
    if (query.length === 0) {
        showSearchMessage("Search cannot be empty");
        return;
    }
    var url = (isMovieSelected()) ? "/movie" : "/tv";
    postRequest(window.location.href + url, {'query':query},
        function (response) {
            if (response.length < 1)
                showSearchMessage('No results found');
            else if (response[0].title === 'badrequest')
                showSearchMessage(response[0].overview);
            else
                addSearchItems(response);
            doNormalButton('main-search-btn','Search');
        },
        function (xhr) {
            console.log(xhr);
            doNormalButton('main-search-btn','Search');
            showSearchMessage('Error searching, error code: '+xhr.status);
        }
    );
}

function addSearchItems(items) {
    showResultsContainer();
    addSearchItem(items[0], false);
    addSearchItemMobile(items[0], false);
    for (var i = 1; i < items.length; i++) {
        addSearchItem(items[i], true);
        addSearchItemMobile(items[i], true);
    }
}

function addSearchItem(item, addBar) {
    var addMethod = (isMovieSelected()) ? 'addMovie('+item.id+')' : 'addTv('+item.id+')';
    var year = '';
    if (item.year !== 0)
        year = 'Year: '+item.year + ' - ';
    var addHtml =
        '<div class="row search-result-row">'+
            '<div class="col-md-3 col-xl-2 col-xxxl-1 align-center">'+
                '<img src="'+item.posterUrl+'" class="search-picture pointer" onclick="window.open(\''+item.infoUrl+'\', \'_blank\')"/>'+
            '</div>'+
            '<div class="col-md-6 col-xl-8 col-xxxl-10" style="height: 225px;">'+
                '<h3 class="search-title">'+item.title+'</h3>'+
                '<h6 class="search-title">'+year+'Rating: '+item.rating+'/10</h6>'+
                '<p class="search-overview">'+item.overview+'</p>'+
            '</div>'+
            '<div class="col-md-3 col-xl-2 col-xxxl-1 request-button-div">'+
                '<button type="button" class="btn type-button" onclick="'+addMethod+'">Request</button>'+
            '</div>'+
        '</div>';
    if (addBar)
        addHtml = '<hr class="search-hr" />' + addHtml;
    $('#search-results-placeholder').append(addHtml);
}

function addSearchItemMobile(item, addBar) {
    var title = item.title;
    if (item.year)
        title += ' ('+item.year+')';
    var addHtml =
        '<div class="row">'+
            '<div class="col-12">'+
                '<h4 class="search-title-mobile">'+title+'</h4>'+
            '</div>'+
            '<div class="col-6 search-picture-div-mobile">'+
                '<img src="'+item.posterUrl+'" class="search-picture-mobile" />'+
            '</div>'+
            '<div class="col-6 request-button-div-mobile">'+
                '<button type="button" class="btn type-button">Request</button>'+
            '</div>'+
        '</div>';
    if (addBar)
        addHtml = '<hr class="search-hr" />' + addHtml;
    $('#search-results-placeholder-mobile').append(addHtml);
}

function addMovie(id) {
    postRequest(window.location.href + "/addMovie", {'tmdbId':id}, function (response) { alert(response.message) }, function (xhr) { console.log(xhr); });
}

function addTv(id) {
    postRequest(window.location.href + "/addTv", {'tvdbId':id}, function (response) { alert(response.message) }, function (xhr) { console.log(xhr); });
}