(function() {
    $( document ).ready(function() {
        $('#reportTime').html($('#reportTime').html() + new Date());
        getData();
    });

    getData = function() {
        const page = $('#page-type').val();
        var url;
        switch(page){
            case "person":
                url = '/person-servlet';
                break;
            case "house":
                url = '/house-servlet';
                break;
            case "property":
                url = '/property-servlet';
                break;
            case "vendor":
                url = '/vendor-servlet';
                break;
            case "zone":
                url = '/zone-servlet';
                break;
            default:
                url = '/person-servlet';
        }

        clearTable();
        $.ajax({
            type: 'GET',
            url: url,
            cache: false,
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