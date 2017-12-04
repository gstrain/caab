colFil = function() {
    column($('#colFilter').val());
}

column = function(p) {
    console.log(p);
    var input, param, table, i, j, row, col;
    input = document.getElementById("search");
    table = document.getElementById("table");
    param = input.value.toUpperCase();
    j = $('th:contains(p)').index();
    row = table.getElementsByTagName("tr");
        for (i = 0; i < row.length; i++) {
            col = row[i].getElementsByTagName("td")[j];
            if (col) {
                if (col.innerHTML.toUpperCase().indexOf(param) > -1) {
                    row[i].style.display = "";
                } else {
                    row[i].style.display = "none";
                }
            }
        }
}
