(function() {

    Table = function($table){
        const $modal = $table.nextAll('#record-modal');
        const $addBtn = $('.btn-add');
        const $reportBtn = $('#reportButton');
        const table = this;
        var pName;
        var pId;
        $.extend(this,{
            page:$('#page-type').val(), // if we ever want more than one table per page, we'll need to remove this from the page and mase it specific to table
            pageTarget: findTarget(this.page),
            $addBtn:$addBtn,
            $reportBtn:$reportBtn,
            $recordAction: $modal.find('.record-action'),
            $modalBtn: $modal.find('#modal-submit'),
            $modal: $modal,
            $Form: $modal.find('.modal-content'),
            $modalForms: $modal.find('.form-control'),
            $recordId: $modal.find('input[name="item-id"]'),

            clearModal:function(){
                this.$modalForms.each(function(index){
                    $(this).val("");
                });
            },

            searchObject:function(obj,name,target,parent){
                var result = null;
                for(var key in obj){
                    if("object" == typeof(obj[key]))
                        result = table.searchObject(obj[key],name,target, key);
                    else if(key == name) {
                        if (parent) {
                            if (target == parent)
                                result = obj[key];
                        }
                        else
                            result = obj[key];
                    }
                    if(result != null)
                        return result;
                }
                return null;
            },

            pathy:function(obj,path){
                var result = null;
                if(path.length > 1) {
                    for (var key in obj) {
                        if ("object" == typeof(obj[key]) && key==path[0]) {
                            path.splice(0, 1);
                            return table.pathy(obj[key], path);
                        }
                    }
                }
                else{
                    return table.searchObject(obj, path[0]);
                }
            },

            add:function(){
                table.clearModal();
                this.$modal.modal('show');
                this.$recordAction.html('Add ');
                if (this.page == 'log') {
                    pId = getParameterByName('fk');
                    pName = getParameterByName('pname');
                }
                this.$recordId.val("");
            },

            edit:function(id){
                table.clearModal();

                $.ajax({
                    type:'GET',
                    url: '/fill-servlet',
                    data: {id:id, table:this.page},
                    dataType: 'json',
                    success: function(response){
                        console.log(response);
                        var value;
                        table.$modalForms.each(function(index){
                            var name = $(this).attr("name");
                            var drop = $(this).attr('data-value-drop');
                            var parent = $(this).attr('data-value-parent');
                            var path = $(this).attr('data-value-path');
                            if(path) {
                                path = path.split('->');
                                value = table.pathy(response, path);
                            }
                            else {
                                if (drop)
                                    value = table.searchObject(response, drop, name);
                                else if (parent)
                                    value = table.searchObject(response, name, parent);
                                else
                                    value = table.searchObject(response, name);
                            }
                            if (value != null)
                                $(this).val(value);
                        });
                    }
                });

                this.$modal.modal('show');
                this.$recordAction.html('Edit ');
                this.$recordId.val(id);
            },

            submit:function(){
                if (this.page == 'log') {
                    var data = {
                        id: this.$recordId.val(),
                        table: this.page,
                        pId: pId,
                        pName: pName
                    };
                } else {
                    var data = {
                        id: this.$recordId.val(),
                        table: this.page
                    }
                }

                var name;
                this.$modalForms.each(function(index){
                    name = $(this).attr("name");
                    data[name] = $(this).val();
                });

                table.$modal.modal('hide').one('hidden.bs.modal',function(){
                    $.ajax({
                        type: 'POST',
                        url: '/write-servlet',
                        data: data,
                        cache: false,
                        success: function () {
                            console.log(data);
                            //console.log('returned');
                            getData();
                            iziToast.success({
                                title: 'OK',
                                timeout: 2500000000000,
                                message: 'Successfully ' + (data.id == 0 ? 'Added' : 'Edited') + ' Record'
                            });
                        },
                        error: function(){
                            console.log(data);
                            iziToast.error({
                                title: 'Unsuccessful!',
                                message: 'Record was not able to be ' + (data.id == 0 ? 'added.' : 'edited.')
                            });
                        }
                    });
                });
            },
            log: function(pk) {
                // console.log(this.page);
                window.location.href = "logs.html" + "?fk=" + pk + "&pname=" + this.page;
                //console.log(window.location.href);
            },
            confirmDelete:function(pk){
                // TODO apply styling to selected row to show which will be deleted
                //console.log(pk);
                $.ajax({
                    type: 'POST',
                    url: '/delete',
                    cache: false,
                    data: $.param({method:'validate', page: this.page, primary_k: pk}),
                    success: function (response) {
                        var arr = response.split('\n');
                        var safe = (arr[0] == 'true');
                        //check true or false
                        if(safe) {
                            iziToast.show({
                                close: false,
                                overlay: true,
                                id: 'deleteDialogue',
                                color: 'red',
                                layout: 2,
                                drag: false,
                                resetOnHover:true,
                                title: 'Deleting Row',
                                icon: 'fa fa-question',
                                message: 'Are you sure? <b>This cannot be undone!</b><br/><input type="checkbox" class="form-check-input" id="deleteCheck"><label for="deleteCheck">&nbsp;&nbsp;&nbsp;I understand</label>',
                                position: 'center',
                                buttons: [
                                    ['<button><b>YES</b></button>', function (instance, toast) {

                                        instance.hide(toast, { transitionOut: 'fadeOut' }, 'button');
                                        table.deleteRow(pk, $('#deleteCheck').prop("checked"));
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
                        }
                        else {
                            var references = '';
                            for(var i=1; i < arr.length; i++)
                                references += arr[i] + '<br/>';
                            var open = false;

                            iziToast.show({
                                close: false,
                                overlay: true,
                                id: 'deleteErrorDialogue',
                                color: 'red',
                                timeout: 8000,
                                drag: false,
                                resetOnHover:true,
                                title: 'TABLE REFERENCES FOUND',
                                position: 'center',
                                icon: 'fa fa-exclamation',
                                message: 'The following table entries reference this row <strong> and WILL ALSO BE DELETED</strong> if this record is removed!<br/><br/>' +
                                    // text section with references
                                '<div id="references">' + references +
                                    '<input type="checkbox" class="" id="deleteCheck"><label for="deleteCheck">&nbsp;&nbsp;&nbsp;I understand</label>' +
                                    '<button id="deleteEverythingButton" class="btn btn-sm" style="margin-left:15px;color:#000; background:rgba(0,0,0,.1);"><strong>DELETE EVERYTHING</strong></button>' +
                                '</div>',
                                buttons: [
                                    ['<button>SHOW</button>', function (instance, toast) {
                                        $('#references').slideToggle();
                                        $('#deleteEverythingButton').on('click', function () {
                                            instance.hide(toast, { transitionOut: 'fadeOut' }, 'button');
                                            table.deleteRow(pk, $('#deleteCheck').prop("checked"));
                                        });
                                    }, true],
                                    ['<button><strong>NO</strong></button>', function (instance, toast) {

                                        instance.hide(toast, { transitionOut: 'fadeOut' }, 'button');

                                    }]
                                ]
                            });
                        }
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
                        cache: false,
                        data: $.param({method:'delete', page: this.page, primary_k: pk}),
                        success: function () {
                            //console.log(' ' + pk);
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
            downloadReport:function() {
                var pageNumber = 1;
                var perPage = 50;
                if(getParameterByName('page'))
                    pageNumber = getParameterByName('page');
                if(getParameterByName('perPage'))
                    perPage = getParameterByName('perPage');
                if(perPage != -1) {
                    window.location = '/pdfgen?page=' + $('#page-type').val() + '&method=table&pageNumber=' + pageNumber + '&perPage=' + perPage;
                    $reportBtn.toggleClass('disabled');
                    $reportBtn.html('Generating...');
                    setTimeout(function () {
                        $reportBtn.toggleClass('disabled');
                        $reportBtn.html('Generate Report');
                    }, 1500); // prevent spamming report generation button
                } else {
                    iziToast.warning({
                        title: 'Caution',
                        message: 'Choose a smaller amount of data per page to generate a report',
                    });
                }
            },
            individualReport:function(pk, button) {
                window.location='/pdfgen?page=' + $('#page-type').val() + '&method=individual&primary_k=' + pk;
                $(button).toggleClass('disabled');
                $(button).html('Generating...');
                setTimeout(function() {
                    $(button).toggleClass('disabled');
                    $(button).html('Report');
                }, 1500); // prevent spamming report generation button
            },
            init:function(){
                this.initButtons();
                //any other things to init
                this.$Form.submit(function(e){
                    table.submit();
                    return false;
                });
            },
            initButtons: function (){
                this.$addBtn.on('click',function(){
                    table.add();
                });

                this.$reportBtn.on('click', function() {
                    table.downloadReport();
                });
                $table.find('.btn-edit').on('click',function(){
                    table.edit(getId(this));
                });

                $table.find('.btn-delete').on('click', function() {
                    table.confirmDelete(getId(this));
                });
                $table.find('.btn-report').on('click', function() {
                    table.individualReport(getId(this), this);
                });
                $table.find('.btn-log').on('click', function() {
                    table.log(getId(this));
                });
            }
        });
        this.init();
    };

    function getId(button){
        return $(button).parent().parent().attr('id').substring(7);
    }

    function findTarget(page){
        switch(page){
            case "person":
                return '/person-servlet';
            case "house":
                return '/house-servlet';
            case "property":
                return '/property-servlet';
            case "organization":
                return '/organization-servlet';
            case "log":
                return '/log-servlet';
            case "zone":
                return '/zone-servlet';
            default:
                return '';
        }
    }
})();