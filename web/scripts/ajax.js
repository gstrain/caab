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


    function undoDelete(pk) {
        $.ajax({
            type: 'GET',
            url: '/delete',
            data: $.param({data_type: 'undo', primary_k: pk}),
            success: function (response) {
                console.log('tried to undo a delete');
                getData();
            }
        });
    }

    function deleteRow(pk) {
        try {
            iziToast.hide($('#deleteDialogue'));
        } catch (exc) {};   // forcibly close the last toast b/c sometimes it would linger
        iziToast.show({
            timeout: 6000,
            close: true,
            overlay: false,
            //toastOnce: true,
            id: 'undoDialogue',
            color: 'red',
            drag: false,
            title: 'Row Deleted',
            icon: 'fa fa-trash',
            message: '',
            position: 'topRight',
            buttons: [
                ['<button><b>UNDO</b></button>', function (instance, toast) {

                    instance.hide(toast, { transitionOut: 'fadeOut' }, 'button');
                    undoDelete(pk);
                }, true],
            ],
            onClosing: function(instance, toast, closedBy){
                console.info('Closing | closedBy: ' + closedBy);
            },
            onClosed: function(instance, toast, closedBy){
                console.info('Closed | closedBy: ' + closedBy);
            }
        });
        $.ajax({
            type: 'GET',
            url: '/delete',
            data: $.param({data_type: 'person', primary_k: pk}),    // TODO globablize data type better?
            success: function(response) {
                console.log(' ' + pk);
                getData(); // reload table after deletion
            }
        });

    }

    function addDeleteListener() {
        $('.button-delete').click(function() {
            var pk = $(this).parent().parent().attr('id').substring(7);
            // TODO apply styling to selected row to show which will be deleted

            iziToast.show({
                timeout: 6000,
                close: false,
                overlay: true,
                //toastOnce: true,
                id: 'deleteDialogue',
                color: 'yellow',
                layout: 2,
                drag: false,
                title: 'Deleting Row',
                icon: 'fa fa-question',
                message: 'Are you sure?',
                position: 'center',
                closeOnEscape: true,
                buttons: [
                    ['<button><b>YES</b></button>', function (instance, toast) {

                        instance.hide(toast, { transitionOut: 'fadeOut' }, 'button');
                        deleteRow(pk);
                    }, true],
                    ['<button>NO</button>', function (instance, toast) {

                        instance.hide(toast, { transitionOut: 'fadeOut' }, 'button');

                    }]
                ],
                onClosing: function(instance, toast, closedBy){
                    console.info('Closing | closedBy: ' + closedBy);
                },
                onClosed: function(instance, toast, closedBy){
                    console.info('Closed | closedBy: ' + closedBy);
                }
            });
        });
    }
})();