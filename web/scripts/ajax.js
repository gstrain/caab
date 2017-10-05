$( document ).ready(function() {
    console.log( "ready!" );
});

function getData() {
    $.ajax({
        type: 'GET',
        url: '/dbservlet',
        success: function (data) { // data should hopefully be a json stream (GSON?)
            // $("#thead").text(data);
            console.log('success');
            console.log(data);
        }
    });
}