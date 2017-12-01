/* Global table search with search bar */
searchTable = function(p) {
    // Declare variables
    var input, filter, table, i, j, row, col;
    input = document.getElementById("search");
    table = document.getElementById("table");
    var count = 0;
    if (p == -1 && !(document.getElementById('filter').checked)) {
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
    } else {
        filter = input.value.toUpperCase();
        row = table.getElementsByTagName("tr");
        for (i = 0; i < row.length; i++) {
            col = row[i].getElementsByTagName("td")[p];
            if (col) {
                if (col.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    row[i].style.display = "";
                } else {
                    row[i].style.display = "none";
                }
            }
        }
    }
};

function filter() {

}

/* Alphabetize columns? */
// function sort(n) {
//     var table, row, flag, i, current, next, flag2, dir,
//         count = 0;
//
//     table = document.getElementById("table");
//     flag = true;
//
//     dir = "asc";
//
//     while (flag) {
//         flag = false;
//
//         row = table.getElementsByTagName("TR");
//
//         for (i = 0; i < row.length - 1; i++) {
//             flag2 = false;
//             current = row[i].getElementsByTagName("TD")[n];
//             next = row[i+1].getElementsByTagName("TD")[n];
//             if (dir == "asc") {
//                 if (current.innerHTML.toLowerCase() > next.innerHTML.toLowerCase()) {
//                     flag2 = true;
//                     break;
//                 }
//             } else if (dir == "desc") {
//                     if (current.innerHTML.toLowerCase() < next.innerHTML.toLowerCase()) {
//                         flag2 = true;
//                         break;
//                     }
//                 }
//             }
//
//             if (flag2) {
//                 row[i].parentNode.insertBefore(row[i+1], row[i]);
//                 flag = true;
//                 count++;
//             } else {
//                 if (count == 0 && dir == "asc") {
//                     dir = "desc";
//                     flag = true;
//                 }
//             }
//
//         }
// }

