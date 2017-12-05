column = function(p) {
    var cInput, cParam, cTable, i, j, row, col;
    cTable = document.getElementById("table");

    searchTable(cTable);

    for (j = 0; j < p; j++) {
        cInput = document.getElementById("search" + j);
        if (cInput.value) {
            // console.log(cInput.value.toUpperCase());
            cParam = cInput.value.toUpperCase();
            row = cTable.getElementsByTagName("tr");
            for (i = 1; i < row.length; i++) {
                    if (row[i].style.display != "none") {
                        col = row[i].getElementsByTagName("td")[j];
                        if (col) {
                            if (col.innerText.toUpperCase().indexOf(cParam) > -1) {
                                row[i].style.display = "";
                            } else {
                                row[i].style.display = "none";
                            }
                        }
                    }
            }
        }
    }
};