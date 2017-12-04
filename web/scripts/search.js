/* Global table search with search bar */
searchTable = function () {
       // Declare variables
       var input, filter, table, i, j, row, col;
       input = document.getElementById("search");
       table = document.getElementById("table");
       filter = input.value.toUpperCase();
       for (i = 1, row; row = table.rows[i]; i++) {
           for (j = 0, col; col = row.cells[j]; j++) {
               if (col) {
                   if (col.innerHTML.toUpperCase().indexOf(filter) > -1) {
                       row.style.display = "";
                       break;
                   } else {
                       row.style.display = "none";
                   }
               }
           }
       }
}

