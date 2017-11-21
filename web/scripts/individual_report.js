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

    /* copied from https://stackoverflow.com/questions/901115/how-can-i-get-query-string-values-in-javascript */
    function getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }
})();