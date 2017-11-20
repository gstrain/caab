(function () {
    // returns the generated content for the report page
    $.ajax({
        type: 'POST',
        url: '/pdfgen',
        cache: false,
        data: $.param({method:'validate', page: this.page, primary_k: pk}),
        success: function (response) {
        }
    });
})();