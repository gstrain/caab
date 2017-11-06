(function() {
    $( document ).ready(function() {
        console.log( "ready!" );
        getData();

    });

    getData = function() {
        clearTable();
        $.ajax({
            type: 'GET',
            url: '/dbservlet',
            success: function (data) {
                console.log('success');
                $('#tableContent').append(data);
                new Table($('.table'))
            }
        });
    };

    function clearTable() {
        $('#tableContent').empty(); // everything
    }

})();