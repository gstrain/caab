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
        const pageNumber = getParameterByName('page');
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
        $('#spinner').show();
        $.ajax({
            type: 'GET',
            url: url,
            cache: false,
            data: $.param({page:pageNumber}),
            success: function (response) {
                console.log('success');
                $('#spinner').hide();

                // remove trailing result size info and use it to build page links
                var index = response.lastIndexOf('resultsSize:');
                var size = response.substring(index+12, response.size); // 12 is length of string 'resultsSize:'
                response = response.substring(0, index);
                console.log('result set size:' + size);

                $('#tableContent').append(response).hide().fadeIn(300);
                new Table($('.table'));

                addPagination(size);
            }
        });
    };

    function clearTable() {
        $('#tableContent').empty(); // everything
    }

    /* copied from https://stackoverflow.com/questions/901115/how-can-i-get-query-string-values-in-javascript */
    getParameterByName = function(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    };

    function addPagination(size) {
        // create a navigation bar with the first & last pages, the current page, two before, and two after - 7 total links
        const RESULTS_PER_PAGE = 50; // needs to match constant in resultSet() method in Functions.java
        const $pagination = $('.pagination');
        const filler = '<li class="page-item disabled"><a class="page-link" href="">. . .</a></li>';
        const currentPage = window.location.href.split('?')[0];
        const currentPageParam = window.location.href.split('?')[1];
        var currentPageNumber = 1;
        var lastPageNumber = Math.ceil(size / RESULTS_PER_PAGE);
        if(currentPageParam) {  // if the url has a parameter, use it, otherwise default to 1
            currentPageNumber = parseInt(currentPageParam.substring(currentPageParam.lastIndexOf('=') + 1, currentPageParam.length));
            // don't allow invalid pages)
            if(currentPageNumber < 1)
                currentPageNumber = 1;
            if(currentPageNumber > lastPageNumber)
                currentPageNumber = lastPageNumber;
        }
        console.log('last page number', lastPageNumber);

        // first page
        $pagination.append('<li class="page-item"><a class="page-link" href="' + currentPage + '?page=1">1</a></li>');
        if(currentPageNumber > 4)
            $pagination.append(filler);

        // 5 in the middle
        if(currentPageNumber-2 > 1)
            $pagination.append('<li class="page-item"><a class="page-link" href="' + currentPage + '?page=' + (currentPageNumber-2) + '">' + (currentPageNumber-2) + '</a></li>');
        if(currentPageNumber-1 > 1)
            $pagination.append('<li class="page-item"><a class="page-link" href="' + currentPage + '?page=' + (currentPageNumber-1) + '">' + (currentPageNumber-1) + '</a></li>');
        if(currentPageNumber !== 1 && currentPageNumber !== lastPageNumber)
            $pagination.append('<li class="page-item"><a class="page-link" href="' + currentPage + '?page=' + currentPageNumber + '">' + currentPageNumber + '</a></li>');
        if(currentPageNumber+1 < lastPageNumber)
            $pagination.append('<li class="page-item"><a class="page-link" href="' + currentPage + '?page=' + (currentPageNumber+1) + '">' + (currentPageNumber+1) + '</a></li>');
        if(currentPageNumber+2 < lastPageNumber)
            $pagination.append('<li class="page-item"><a class="page-link" href="' + currentPage + '?page=' + (currentPageNumber+2) + '">' + (currentPageNumber+2) + '</a></li>');

        // last page
        if(currentPageNumber < lastPageNumber-3)
            $pagination.append(filler);
        if(currentPageNumber !== lastPageNumber && currentPageNumber <= lastPageNumber)
            $pagination.append('<li class="page-item"><a class="page-link" href="' + currentPage + '?page=' + lastPageNumber+ '">' + lastPageNumber + '</a></li>');

        // highlight active page
        $('.page-item').each(function() {
            if(parseInt($(this).text()) === currentPageNumber) {
                $(this).addClass('active');
                return false; // stop iterating once we find the correct page
            }
        });

        // add selector box and fill
        if(lastPageNumber > 5) {
            $pagination.append('<form id="pagesForm" method="GET" action="' + currentPage + '">' +
                '<select class="form-control" name="page" id="pageSelector" onchange="this.form.submit()">' +
                '</select>' +
                '</form>');
            for (var i = 1; i <= lastPageNumber; i++) {
                if (i === currentPageNumber)
                    $('#pageSelector').append('<option selected="selected" value="' + i + '">' + i + '</option>');
                else
                    $('#pageSelector').append('<option value="' + i + '">' + i + '</option>')
            }
        }
    }
})();
