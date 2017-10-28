(function() {

    Table = function($table){
        const $modal = $table.nextAll('#record-modal');
        const $addBtn = $(".btn-add");
        const that = this;
        $.extend(this,{
            $addBtn:$addBtn,
            $recordAction: $modal.find('.record-action'),
            $modal: $modal,

            add:function(){
                this.$recordAction.html('Add ');
            },

            edit:function(){
                this.$recordAction.html('Edit ');
            },
            init:function(){
                this.initButtons();
                //any other things to init
            },
            initButtons: function (){
                this.$addBtn.on("click",function(){
                    that.add();
                });

                $table.find(".btn-edit").on("click",function(){
                    that.edit();
                });
            }
        });
        this.init();
    };

    new Table($('table'));

})();