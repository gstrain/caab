column = function(p) {
    var input, param, table, i, j, row, col;
    input = document.getElementById("search");
    table = document.getElementById("table");
    param = input.value.toUpperCase();
    row = table.getElementsByTagName("tr");
    for (i = 0; i < row.length; i++) {
        col = row[i].getElementsByTagName("td")[p];
        if (col) {
            if (col.innerHTML.toUpperCase().indexOf(param) > -1) {
                row[i].style.display = "";
            } else {
                row[i].style.display = "none";
            }
        }
    }
}
