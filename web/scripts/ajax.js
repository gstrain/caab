(function() {
    $( document ).ready(function() {
        console.log( "ready!" );
        getData();
    });

    function getData() {
        clearTable();
        $.ajax({
            type: 'GET',
            url: '/dbservlet',
            success: function (data) {
                console.log('success');
                $('#nav').after(data);
                // attach listener to delete buttons
                $('.button-delete').click(function() {
                    var pk = $(this).parent().parent().attr('id').substring(7);
                    // TODO apply styling to selected row to show which will be deleted
                   if(confirm('Are you sure you want to delete this property?')) {
                        // delete the row from the db
                       $.ajax({
                          type: 'GET',
                          url: '/delete',
                          data: jQuery.param({data_type: 'person', primary_k: pk}),    // TODO globablize data type better?
                          success: function(response) {
                              console.log('deleted person with pk ' + pk);
                              // clearTable();
                              // getData();
                              window.location.reload();
                           }
                       });
                   }
                });
            }
        });
    }

    function clearTable() {
        $('#table').remove();
        $('#addButton').remove();
    }
})();