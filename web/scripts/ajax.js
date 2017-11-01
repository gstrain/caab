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
                addDeleteListener();
            }
        });
    }

    function clearTable() {
        $('#table').remove();
        $('#addButton').remove();
    }

    function deleteRow(pk, checked) {
        var toastMessage = 'Operation aborted by not giving consent.';
        if(checked) {
            toastMessage = 'Row successfully deleted';
            $.ajax({
                type: 'POST',
                url: '/delete',
                data: $.param({data_type: 'person', primary_k: pk}),    // TODO globablize data type better?
                success: function (response) {
                    console.log(' ' + pk);
                    getData(); // reload table after deletion
                }
            });
        }
        iziToast.show({
            title: 'Row Deletion',
            message: toastMessage,
            timeout: 3500,
            color: 'blue',
            icon: 'fa fa-info-circle',
            layout: 2,
            position: 'bottomRight',
            progressBar: false
        });
    }

    function addDeleteListener() {
        $('.button-delete').click(function() {
            var pk = $(this).parent().parent().attr('id').substring(7);
            var checked = false;
            // TODO apply styling to selected row to show which will be deleted
            iziToast.show({
                timeout: 6000,
                close: false,
                overlay: true,
                //toastOnce: true,
                id: 'deleteDialogue',
                color: 'red',
                layout: 2,
                drag: false,
                title: 'Deleting Row',
                icon: 'fa fa-question',
                message: 'Are you sure? <b>This cannot be undone!</b><br/><input type="checkbox" class="form-check-input" id="deleteCheck"><label for="deleteCheck">I understand</label>',
                position: 'center',
                closeOnEscape: true,
                buttons: [
                    ['<button><b>YES</b></button>', function (instance, toast) {

                        instance.hide(toast, { transitionOut: 'fadeOut' }, 'button');
                            deleteRow(pk, $('#deleteCheck').prop("checked"));
                    }, true],
                    ['<button id="no">NO</button>', function (instance, toast) {

                        instance.hide(toast, { transitionOut: 'fadeOut' }, 'button');

                    }]
                ],
                onClosing: function(instance, toast, closedBy){
                    console.info('Closing | closedBy: ' + closedBy);
                },
                onClosed: function(instance, toast, closedBy){
                    console.info('Closed | closedBy: ' + closedBy);
                    $('deleteCheck').remove();
                }
            });
        });
    }
})();