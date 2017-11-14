(function() {
    $( document ).ready(function() {
        var date = new Date();
        var dateString = ((date.getMonth() + 1) + '/' + date.getDate() + '/' +  date.getFullYear());
        var meridian = date.getHours() > 12 ? 'PM' : 'AM';
        var timeString = (((date.getHours() > 12) ? date.getHours()-12 : date.getHours()) + ":") + date.getMinutes() + ' ' + meridian;
        $('#reportTime').html($('#reportTime').html() + timeString + '<br/>' + dateString);
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
            case "log":
                url = '/log-servlet';
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