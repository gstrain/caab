function myFunction() {
    // Declare variables
    var input, filter, table, tr, i, j, row, col;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("table");
    tr = table.getElementsByTagName("tr");

    for (i = 0, row; row = table.rows[i]; i++) {
        for (j = 0, col; col = row.cells[j]; j++) {
            td = tr[i].getElementsByTagName("td")[i];
            console.log(td);
            if (td) {
              if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
                break;
              } else {
                tr[i].style.display = "none";
              }
            }
        }
    }
}

