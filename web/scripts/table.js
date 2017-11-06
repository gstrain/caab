(function() {

    Table = function($table){
        const $modal = $table.nextAll('#record-modal');
        const $addBtn = $('.btn-add');
        const thing = this;
        $.extend(this,{
            $addBtn:$addBtn,
            $recordAction: $modal.find('.record-action'),
            $modal: $modal,

            add:function(){
                this.$modal.modal('show');
                this.$recordAction.html('Add ');
            },

            edit:function(){
                this.$modal.modal('show');
                this.$recordAction.html('Edit ');
            },
            confirmDelete:function(button){
                var pk = $(button).parent().parent().attr('id').substring(7);
                // TODO apply styling to selected row to show which will be deleted
                iziToast.show({
                    timeout: 6000,
                    close: false,
                    overlay: true,
                    id: 'deleteDialogue',
                    color: 'red',
                    layout: 2,
                    drag: false,
                    title: 'Deleting Row',
                    icon: 'fa fa-question',
                    message: 'Are you sure? <b>This cannot be undone!</b><br/><input type="checkbox" class="form-check-input" id="deleteCheck"><label for="deleteCheck">&nbsp;&nbsp;&nbsp;I understand</label>',
                    position: 'center',
                    closeOnEscape: true,
                    buttons: [
                        ['<button><b>YES</b></button>', function (instance, toast) {

                            instance.hide(toast, { transitionOut: 'fadeOut' }, 'button');
                            thing.deleteRow(pk, $('#deleteCheck').prop("checked"));
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
                    }
                });
            },
            deleteRow:function(pk, checked) {
                var toastMessage = 'Operation aborted by not giving consent.';
                if(checked) {
                    toastMessage = 'Row successfully deleted';
                    $.ajax({
                        type: 'POST',
                        url: '/delete',
                        data: $.param({data_type: 'Person', primary_k: pk}),    // TODO globablize data type better?
                        success: function () {
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
            },
            init:function(){
                this.initButtons();
                //any other things to init
            },
            initButtons: function (){
                this.$addBtn.on('click',function(){
                    thing.add();
                });

                $table.find('.btn-edit').on('click',function(){
                    thing.edit();
                });

                $table.find('.btn-delete').on('click', function() {
                    thing.confirmDelete(this);
                });
            }
        });
        this.init();
    };
})();