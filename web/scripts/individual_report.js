(function () {
    $(document).ready(function() {
        // returns the generated content for the report page
        var pk = getParameterByName('primary_k');
        var page = getParameterByName('page');
        $.ajax({
            type: 'POST',
            url: '/pdfgen',
            cache: false,
            data: $.param({page: page, primary_k: pk}),
            success: function (response) {
                $('#reportContent').append(response);
                $('#houseID').html($('#houseID').html() + pk);
            }
        });
    });


})();