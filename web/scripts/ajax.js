$( document ).ready(function() {
    console.log( "ready!" );
    getData();
});

function getData() {
    $.ajax({
        type: 'GET',
        url: '/dbservlet',
        success: function (data) {
            console.log('success');
            $('#nav').after(data);
        }
    });
}