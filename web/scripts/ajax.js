$( document ).ready(function() {
    console.log( "ready!" );
    getData();
});

function getData() {
    $.ajax({
        type: 'GET',
        url: '/navbar',
        success: function (data) { // data should hopefully be a json stream (GSON?)
            // $("#thead").text(data);
            console.log('success');
            $('body').append(data);
            // $('*').css('visibility', 'visible');
        }
    });
}